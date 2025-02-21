package com.br.pagme.devedor.entidade;

import com.br.pagme.EntidadePersistente;
import com.br.pagme.usuario.entidade.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "DEV_DEVEDOR", schema = "PGM")
@AttributeOverride(name = "id", column = @Column(name = "DEV_ID", columnDefinition = "BIGINT"))
public class Devedor extends EntidadePersistente<Long> {

    @Column(name = "DEV_DS_NOME", nullable = false, columnDefinition = "VARCHAR(150)")
    private String nome;
    @Column(name = "DEV_DS_SOBRENOME", nullable = false, columnDefinition = "VARCHAR(150)")
    private String sobrenome;
    @Column(name = "DEV_CD_TELEFONE", nullable = false, columnDefinition = "VARCHAR(12)")
    private String telefonr;
    @ManyToOne
    @JoinColumn(name = "USU_ID")
    private Usuario usuario;


}
