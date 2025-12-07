package br.pagme.controller.usuario.resources;

import br.pagme.enums.RolesEnum;
import br.pagme.enums.StatusEnum;
import jakarta.persistence.Column;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UsuarioResources(
        Long id,
        String username,
        String password,
        String nome,
        String email,
        String telefone,
        String status,
        LocalDateTime criadoEm,
        RolesEnum role

){

}
