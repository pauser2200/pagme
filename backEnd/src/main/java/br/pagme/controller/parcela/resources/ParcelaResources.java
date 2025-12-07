package br.pagme.controller.divida.resources;

import br.pagme.controller.usuario.resources.UsuarioResources;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record DividaResources(
        String descricao,
        BigDecimal valorTotal,
        Integer qtdParcelas,
        Boolean ativo,
        LocalDateTime criadoEm,
        UsuarioResources devedor
) {

}
