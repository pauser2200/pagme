package br.pagme.domain.divida.entidades;

import br.pagme.domain.EntidadePersistente;
import br.pagme.domain.usuario.entidades.Usuario;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "DIV_DIVIDA")
@AttributeOverride(name ="id",column = @Column(name = "DIV_ID", columnDefinition = "BIGINT"))
public class Divida extends EntidadePersistente<Long> {

    @Column(name = "DIV_DS_DESCRICAO", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @Column(name = "DIV_VL_VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "DIV_QTD_PARCELA", nullable = false)
    private Integer qtdParcelas;

    @Column(name = "DIV_IB_ATIVO", nullable = false)
    private Boolean ativo;

    @Column(name = "DIV_DH_CRIACAO")
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "USU_ID",  nullable = false)
    private Usuario devedor;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }





}
