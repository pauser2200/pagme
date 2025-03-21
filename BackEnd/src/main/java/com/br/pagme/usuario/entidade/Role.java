package com.br.pagme.usuario.entidade;

import com.br.pagme.EntidadePersistente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "ROL_ROLES", schema = "PGM")
@AttributeOverride(name ="id",column = @Column(name = "ROL_ID", columnDefinition = "BIGINT"))
public class Role extends EntidadePersistente<Long> {

    @Column(name = "ROL_DS_NAME")
    private Long roleId;

}
