package br.pagme.autenticacao.controller;

import br.pagme.infra.security.DadosTokenJWT;
import br.pagme.autenticacao.servico.AutenticacaoServico;
import br.pagme.domain.usuario.entidades.Usuario;
import br.pagme.controller.usuario.resources.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class AutenticacaoController {

    private final AutenticacaoServico tokenServico;
    private final AuthenticationManager manager;


    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid LoginRequest loginRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenServico.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
