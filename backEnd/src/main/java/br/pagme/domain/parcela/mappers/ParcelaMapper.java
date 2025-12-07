package br.pagme.domain.parcela.mappers;

import br.pagme.controller.parcela.resources.ParcelaResources;
import br.pagme.domain.parcela.entidades.Parcela;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ParcelaMapper {

    Parcela toEntity(ParcelaResources resources);

    ParcelaResources toDto(Parcela Parcela);

    List<ParcelaResources> toDto(List<Parcela> Parcelas);

}
