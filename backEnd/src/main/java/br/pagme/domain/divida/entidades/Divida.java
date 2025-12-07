package br.pagme.domain.devedor.entidades;

import br.pagme.domain.EntidadePersistente;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "DEV_DEVEDOR")
@AttributeOverride(name ="id",column = @Column(name = "DEV_ID", columnDefinition = "BIGINT"))
public class Devedor extends EntidadePersistente<Long> {

    @Column(name = "DEV_DS_NOME", nullable = false, columnDefinition = "VARCHAR(20)")
    private String nome;

    @Column(name = "DEV_DS_CPF", nullable = false)
    private String cpf;

    @Column(name = "DEV_DS_EMAIL", nullable = false)
    private String email;

    @Column(name = "DEV_IB_ATIVO", nullable = false)
    @Builder.Default
    private boolean ativo = true;

    @Column(name = "USU_DH_CRIACAO")
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }





}
