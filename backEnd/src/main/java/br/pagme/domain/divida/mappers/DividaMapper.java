package br.pagme.domain.divida.mappers;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.domain.divida.entidades.Divida;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DividaMapper {

    Divida toEntity(DividaResources resources);

    DividaResources toDto(Divida entity);

    List<DividaResources> toDto(List<Divida> Dividas);

}
