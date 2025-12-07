package br.pagme.domain.parcela.entidades;

import br.pagme.domain.EntidadePersistente;
import br.pagme.domain.divida.entidades.Divida;
import br.pagme.domain.usuario.entidades.Usuario;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "PRC_PARCELA")
@AttributeOverride(name ="id",column = @Column(name = "PRC_ID", columnDefinition = "BIGINT"))
public class Parcela extends EntidadePersistente<Long> {

    @Column(name = "PRC_NU_PARCELA", nullable = false)
    private Integer qtdParcelas;

    @Column(name = "PRC_VL_PARCELA", nullable = false)
    private BigDecimal valorParcela;

    @Column(name = "PRC_DT_VENCIMENTO")
    private LocalDate dataVencimento;

    @Column(name = "PRC_IB_ATIVO", nullable = false)
    private Boolean ativo;

    @Column(name = "PRC_DH_CRIACAO")
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "DIV_ID",  nullable = false)
    private Divida divida;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }





}
