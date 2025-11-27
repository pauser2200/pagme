package br.pagme.controller.usuario.resources;

import lombok.Builder;

@Builder
public record RolesResources(Long id, String name) {
}