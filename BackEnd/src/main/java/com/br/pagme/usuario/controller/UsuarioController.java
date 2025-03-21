package com.br.pagme.usuario.controller;

import com.br.pagme.usuario.resources.UsuarioResources;
import com.br.pagme.usuario.servico.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/usuarios")
public class UsuarioController {


    private final UsuarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioResources>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<UsuarioResources> salvar(@RequestBody UsuarioResources body) {
        return ResponseEntity.ok(service.salvar(body));
    }
}
