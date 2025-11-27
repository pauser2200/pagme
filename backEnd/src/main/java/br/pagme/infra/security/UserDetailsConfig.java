package br.pagme.infra.security;

import br.pagme.repositpry.usuario.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@Configuration
public class UserDetailsConfig {

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repo) {
        return login -> {
            log.debug("UserDetailsService: procurando usuário '{}'", login);
            return repo.findByUsername(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
        };
    }
}
