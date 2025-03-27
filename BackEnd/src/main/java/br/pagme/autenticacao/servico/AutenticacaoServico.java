package br.pagme.autenticacao.servico;

import br.pagme.domain.usuario.entidades.Role;
import br.pagme.domain.usuario.entidades.Usuario;
import br.pagme.repositpry.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutenticacaoServico implements UserDetailsService {

    @Value("${jwt.publickey}")
    private RSAPublicKey publicKey;

    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public String gerarToken(Usuario user) {
        return jwtEncoder.encode(JwtEncoderParameters
                        .from(getClaims(user)))
                .getTokenValue();
    }

    private String getScopes(Usuario user) {
        return user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));
    }

    private JwtClaimsSet getClaims(Usuario user) {
        var now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(Objects.requireNonNull(user.getUsername()))
                .issuedAt(now)
                .expiresAt(dataExpiracao())
                .claim("scope", getScopes(user))
                .build();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).orElseThrow();
    }
}
