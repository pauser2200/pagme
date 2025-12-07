package br.pagme.service.divida;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.domain.divida.mappers.DividaMapper;
import br.pagme.repository.divida.DividaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DividaService {


    private final DividaRepository repository;
    private final DividaMapper  mapper;
    public DividaResources salvar(DividaResources resource){
        var divida = this.mapper.toEntity(resource);
        return this.mapper.toDto(repository.save(divida));

    }

}
