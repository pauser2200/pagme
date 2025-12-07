package br.pagme.controller.parcela.resources;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.controller.usuario.resources.UsuarioResources;
import br.pagme.domain.divida.entidades.Divida;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ParcelaResources(
        Integer qtdParcelas,
        BigDecimal valorParcela,
        LocalDate dataVencimento,
        Boolean ativo,
        LocalDateTime criadoEm,
        DividaResources divida

) {

}
