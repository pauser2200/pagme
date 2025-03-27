package br.pagme.controller.usuario.resources;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResources implements Serializable {

    private Long id;
    private String username;
    private String password;
    private List<RolesResources> roles;


}
