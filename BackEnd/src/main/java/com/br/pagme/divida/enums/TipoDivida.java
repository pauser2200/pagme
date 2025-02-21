package com.br.pagme.divida.enums;

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
