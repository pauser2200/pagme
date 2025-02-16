package com.br.pagme.divida.entidade;

import com.br.pagme.EntidadePersistente;
import com.br.pagme.devedor.entidade.Devedor;
import com.br.pagme.divida.enums.TipoDivida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "DIV_DIVIDA", schema = "PGM")
@AttributeOverride(name = "id", column = @Column(name = "DIV_ID", columnDefinition = "BIGINT"))
public class Divida extends EntidadePersistente<Long> {

    @Column(name = "DIV_CD_TIPO_DIVIDA", nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private TipoDivida tipoDivida;
    @Column(name = "DIV_DT_DATA_INICIO", nullable = false, columnDefinition = "DATE")
    private LocalDate dataInicio;
    @Column(name = "DIV_DT_DATA_FIM", columnDefinition = "DATE")
    private LocalDate dataFim;
    @Column(name = "DIV_CD_FINAL_CARTAO", nullable = false , columnDefinition = "INTEGER")
    private Integer digitosFinalCartao;
    @Column(name = "DIV_VL_VALOR_TOTAL", nullable = false, columnDefinition = "DECIMAL(19,2)")
    private BigDecimal valorTotalDivida;
    @Column(name = "DIV_NU_QTD_PARCELAS", nullable = false, columnDefinition = "INTEGER")
    private Integer quantidadeParcelas;
    @Column(name = "DIV_DS_DESCRICAO", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricaoDivida;
    @ManyToOne
    @JoinColumn(name = "DEV_ID", nullable = false)
    private Devedor devedor;
}
