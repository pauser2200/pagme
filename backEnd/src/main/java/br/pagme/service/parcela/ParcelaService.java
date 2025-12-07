package br.pagme.service.parcela;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.controller.parcela.resources.ParcelaResources;
import br.pagme.domain.divida.entidades.Divida;
import br.pagme.domain.parcela.entidades.Parcela;
import br.pagme.domain.parcela.mappers.ParcelaMapper;
import br.pagme.repository.parcela.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelaService {


    private final ParcelaRepository repository;
    private final ParcelaMapper mapper;

    public List<ParcelaResources> listar() {
        List<Parcela> parcelas = repository.findAll();
        return this.mapper.toDto(parcelas);
    }

    public ParcelaResources salvar(ParcelaResources resource){
        var percela = this.mapper.toEntity(resource);
        return this.mapper.toDto(repository.save(percela));

    }

    public void excluir(Long id) {
        var parcela = this.repository.findById(id).orElseThrow();
        this.repository.delete(parcela);

    }

}
