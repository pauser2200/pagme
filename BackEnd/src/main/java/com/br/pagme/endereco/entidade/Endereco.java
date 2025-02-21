package com.br.pagme.endereco.entidade;

import com.br.pagme.EntidadePersistente;
import com.br.pagme.devedor.entidade.Devedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "DEV_ENDERECO", schema = "PGM")
@AttributeOverride(name = "id", column = @Column(name = "END_ID", columnDefinition = "BIGINT"))
public class Endereco extends EntidadePersistente<Long> {

    @Column(name = "END_CD_CEP", nullable = false, columnDefinition = "VARCHAR(10)")
    private String cep;
    @Column(name = "END_DS_ENDERECO", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricaoEndereco;
    @Column(name = "END_NU_NUMERO", nullable = false, columnDefinition = "INTEGER")
    private Integer numero;

    @Column(name = "END_DS_COMPLEMENTO", nullable = false, columnDefinition = "VARCHAR(200)")
    private String complemento;
    @Column(name = "END_DS_BAIRRO", nullable = false, columnDefinition = "VARCHAR(100)")
    private String bairro;
    @Column(name = "END_DS_CIDADE", nullable = false, columnDefinition = "VARCHAR(50)")
    private String cidade;
    @Column(name = "END_CD_ESTADO", nullable = false, columnDefinition = "VARCHAR(2)")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "DEV_ID", nullable = false)
    private Devedor devedor;


}
