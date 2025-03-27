package br.pagme.domain.usuario.entidades;

import br.pagme.domain.EntidadePersistente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "ROL_ROLES", schema = "PGM")
@AttributeOverride(name ="id",column = @Column(name = "ROL_ID", columnDefinition = "BIGINT"))
public class Role extends EntidadePersistente<Long> {

    @Column(name = "ROL_DS_NAME")
    private String name;

}
