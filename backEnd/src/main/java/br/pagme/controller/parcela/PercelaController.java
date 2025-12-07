package br.pagme.controller.parcela;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.controller.parcela.resources.ParcelaResources;
import br.pagme.service.divida.DividaService;
import br.pagme.service.parcela.ParcelaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/parcelas")
public class PercelaController {


    private final ParcelaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<ParcelaResources>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ParcelaResources> salvar(@RequestBody ParcelaResources body) {
        return ResponseEntity.ok(service.salvar(body));
    }

    @DeleteMapping(value = "/{idUsuario}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long idUsuario) {
        service.excluir(idUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
