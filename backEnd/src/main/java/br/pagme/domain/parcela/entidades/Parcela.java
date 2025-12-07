package br.pagme.domain.cartao.entidades;

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
@Table(name = "CRT_CARTAO")
@AttributeOverride(name ="id",column = @Column(name = "CRT_ID", columnDefinition = "BIGINT"))
public class Cartao extends EntidadePersistente<Long> {

    @Column(name = "DIV_DS_nome", nullable = false, columnDefinition = "VARCHAR(20)")
    private String nome;

    @Column(name = "DIV_QTD_PARCEAS", nullable = false)
    private Integer diaFechamento;

    @Column(name = "DIV_QTD_PARCEAS", nullable = false)
    private Integer diaVencimento;

    @Column(name = "DIV_IB_ATIVO", nullable = false)
    @Builder.Default
    private Boolean ativo;

    @Column(name = "DIV_DH_CRIACAO")
    private LocalDateTime criadoEm;

    @ManyToMany
    @JoinColumn(name = "USU_ID",  nullable = false)
    private Usuario devedor;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }





}
