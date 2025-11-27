package br.horus.service.autenticacao;

import br.horus.domain.usuario.entidades.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AutenticacaoServico {

    private final JwtEncoder jwtEncoder;
    private final String issuer;
    private final long accessMinutes;

    public AutenticacaoServico(
            JwtEncoder jwtEncoder,
            @Value("${app.security.jwt.issuer:horus}") String issuer,
            @Value("${app.security.jwt.expires-minutes:60}") long accessMinutes
    ) {
        this.jwtEncoder = jwtEncoder;
        this.issuer = issuer;
        this.accessMinutes = accessMinutes;
    }

    /** Gera um JWT assinado com a chave privada (RSA), contendo username e roles. */
    public String gerarToken(Usuario usuario) {
        Instant now = Instant.now();

        // Extrai authorities/roles do UserDetails (ex.: ["ADMIN","BASIC"])
        List<String> roles = usuario.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Claims do token
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plus(accessMinutes, ChronoUnit.MINUTES))
                .subject(usuario.getUsername())   // "sub": quem é o dono do token
                .claim("roles", roles)      // lista de perfis do usuário
                .build();

        // Assina e retorna o token compacto (JWS)
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
