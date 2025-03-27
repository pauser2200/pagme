package br.pagme.controller.usuario;

import br.pagme.controller.usuario.resources.UsuarioResources;
import br.pagme.servico.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/usuarios")
public class UsuarioController {


    private final UsuarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UsuarioResources>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping(value = "/salvar")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<UsuarioResources> salvar(@RequestBody UsuarioResources body) {
        return ResponseEntity.ok(service.salvar(body));
    }

    @DeleteMapping(value = "/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long idUsuario) {
        service.excluir(idUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
