package com.br.pagme.divida.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDivida {
    CREDITO("Crédito"),
    EMPRESTIMO("Empréstimo"),
    DEBITO("Débito"),
    DINHEIRO("Dinheiro");

    private String descricao;
}
