package br.horus.controller.autenticacao;

import br.horus.controller.usuario.resources.LoginRequest;
import br.horus.domain.usuario.entidades.Usuario;
import br.horus.infra.security.DadosTokenJWT;
import br.horus.service.autenticacao.AutenticacaoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class AutenticacaoController {

    // Serviço que GERA o JWT (assina com a sua chave privada).
    // Deve expor algo como: ‘String’ gerarToken(Usuario usuario);
    private final AutenticacaoServico autenticacaoServico;

    // Gerenciador que AUTENTICA username/senha usando seu UserDetailsService + PasswordEncoder.
    private final AuthenticationManager authenticationManager;

    /**
     * Faz o login e retorna o token JWT.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid LoginRequest loginRequest) {
        // Cria o "ticket" de autenticação com as credenciais:
        var authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.username(),
                loginRequest.password()
        );

        // Autentica (vai buscar utilizador no banco e comparar a senha com BCrypt):
        Authentication authentication = authenticationManager.authenticate(authToken);

        // Pega o principal (sua entidade Usuario implementa UserDetails):
        Usuario usuario = (Usuario) authentication.getPrincipal();

        // Gera o JWT com claims (roles, etc.) e assina:
        String tokenJWT = autenticacaoServico.gerarToken(usuario);

        // Responde com o ‘token’ no formato que você já usa (record DadosTokenJWT):
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }


}
