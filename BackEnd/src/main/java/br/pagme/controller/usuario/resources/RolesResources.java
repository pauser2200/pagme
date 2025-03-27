package br.pagme.controller.usuario.resources;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RolesResources implements Serializable {
    private Long id;
    private String name;
}