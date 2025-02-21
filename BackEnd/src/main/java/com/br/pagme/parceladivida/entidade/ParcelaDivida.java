package com.br.pagme.parceladivida.entidade;

import com.br.pagme.EntidadePersistente;
import com.br.pagme.divida.entidade.Divida;
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
@Table(name = "PRD_PARCELA_DIVIDA", schema = "PGM")
@AttributeOverride(name = "id", column = @Column(name = "PRD_ID", columnDefinition = "BIGINT"))
public class ParcelaDivida extends EntidadePersistente<Long> {

    @Column(name = "PRD_VL_VALOR_PARCELA", nullable = false, columnDefinition = "DECIMAL(19,2)")
    private BigDecimal valorParcela;
    @Column(name = "PRD_DT_DATA_VENCIMENTO_PARCELA", nullable = false, columnDefinition = "DATE")
    private LocalDate dataVencimento;
    @Column(name = "PRD_DT_DATA_PAGAMENTO_PARCELA", nullable = false, columnDefinition = "DATE")
    private LocalDate dataPagamento;
    @Column(name = "PRD_IB_PAGO", columnDefinition = "SMALLINT")
    private Boolean ibPago;
    @ManyToOne
    @JoinColumn(name = "DIV_ID", nullable = false)
    private Divida divida;

}
