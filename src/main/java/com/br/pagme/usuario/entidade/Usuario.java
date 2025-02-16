package com.br.pagme.usuario.entidade;

import com.br.pagme.EntidadePersistente;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @Column(name = "USU_CD_TOKEN", nullable = false)
    private String token;

}
