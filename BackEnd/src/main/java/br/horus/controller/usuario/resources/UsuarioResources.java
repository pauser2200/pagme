package br.horus.controller.usuario.resources;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioResources(Long id, String username, String password, List<RolesResources> roles){

}
