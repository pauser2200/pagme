package br.horus.infra.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

@Configuration
public class JwtKeysConfig {

    @Value("${jwt.publickey}")
    private Resource publicKeyRes;

    @Value("${jwt.privatekey}")
    private Resource privateKeyRes;

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
        RSAPublicKey pub = readPublicKey(publicKeyRes);
        return NimbusJwtDecoder.withPublicKey(pub).build();
    }

    @Bean
    JwtEncoder jwtEncoder() throws Exception {
        RSAPublicKey pub = readPublicKey(publicKeyRes);
        RSAPrivateKey priv = readPrivateKey(privateKeyRes);
        JWK jwk = new RSAKey.Builder(pub).privateKey(priv).build();
        JWKSource<SecurityContext> jwks = (selector, ctx) -> List.of(jwk);
        return new NimbusJwtEncoder(jwks);
    }

    private RSAPublicKey readPublicKey(Resource res) throws Exception {
        String pem = res.getContentAsString(StandardCharsets.UTF_8);
        String content = pem.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] der = Base64.getDecoder().decode(content);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(der));
    }

    private RSAPrivateKey readPrivateKey(Resource res) throws Exception {
        String pem = res.getContentAsString(StandardCharsets.UTF_8);
        String content = pem.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] der = Base64.getDecoder().decode(content);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(der));
    }
}
