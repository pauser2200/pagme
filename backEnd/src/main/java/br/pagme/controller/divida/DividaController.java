package br.pagme.controller.divida;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.service.divida.DividaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dividas")
public class DividaController {


    private final DividaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<DividaResources>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping(value = "/{idDevedor}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<DividaResources>> listarPorDevedor(@PathVariable Long idDevedor) {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DividaResources> salvar(@RequestBody DividaResources body) {
        return ResponseEntity.ok(service.salvar(body));
    }

    @DeleteMapping(value = "/{idUsuario}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long idUsuario) {
        service.excluir(idUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
