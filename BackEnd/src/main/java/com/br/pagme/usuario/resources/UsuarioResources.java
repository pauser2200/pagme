package com.br.pagme.usuario.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResources implements Serializable {

    private String login;
    private String senha;
    private String token;


}
