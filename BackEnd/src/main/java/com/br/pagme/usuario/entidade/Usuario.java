package com.br.pagme.usuario.entidade;

import com.br.pagme.EntidadePersistente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "USU_USUARIO", schema = "PGM")
@AttributeOverride(name ="id",column = @Column(name = "USU_ID", columnDefinition = "BIGINT"))
public class Usuario extends EntidadePersistente<Long> {

    @Column(name = "USU_DS_LOGIN", nullable = false, columnDefinition = "VARCHAR(20)")
    private String login;

    @Column(name = "USU_DS_SENHA", nullable = false, columnDefinition = "VARCHAR(8)")
    private String senha;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USR_USUARIOS_ROLES",
            joinColumns = @JoinColumn(name = "USU_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID")
    )
    private Set<Role> roles;

}
