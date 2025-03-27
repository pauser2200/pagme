package br.pagme.domain.divida.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDivida {
    CREDITO,
    EMPRESTIMO,
    DEBITO,
    DINHEIRO;
}
