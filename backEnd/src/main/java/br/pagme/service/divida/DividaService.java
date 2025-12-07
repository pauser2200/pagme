package br.pagme.service.divida;

import br.pagme.controller.divida.resources.DividaResources;
import br.pagme.controller.usuario.resources.UsuarioResources;
import br.pagme.domain.divida.entidades.Divida;
import br.pagme.domain.divida.mappers.DividaMapper;
import br.pagme.domain.usuario.entidades.Usuario;
import br.pagme.repository.divida.DividaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DividaService {


    private final DividaRepository repository;
    private final DividaMapper mapper;


    public List<DividaResources> listar() {
        List<Divida> dividas = repository.findAll();
        return this.mapper.toDto(dividas);
    }

    public List<DividaResources> listarporDevedor(Long idDevedor) {
        List<Divida> dividas = repository.findAll();
        return this.mapper.toDto(dividas);
    }

    public DividaResources salvar(DividaResources resource) {
        var divida = this.mapper.toEntity(resource);
        return this.mapper.toDto(repository.save(divida));

    }

    public void excluir(Long id) {
        var divida = this.repository.findById(id).orElseThrow();
        this.repository.delete(divida);

    }

}
