package br.horus.service.usuario;

import br.horus.controller.usuario.resources.UsuarioResources;
import br.horus.domain.usuario.entidades.Usuario;
import br.horus.domain.usuario.mappers.UsuarioMapper;
import br.horus.repositpry.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected final UsuarioMapper mapper;

    public List<UsuarioResources> listar() {
        List<Usuario> usuarios = repository.findAll();
        return this.mapper.toDto(usuarios);
    }

    public UsuarioResources salvar(UsuarioResources resources) {
        var usuario = this.mapper.toEntity(resources);
//        usuario.setPassword(bCryptPasswordEncoder.encode(resources.getPassword()));
        return this.mapper.toDto(this.repository.save(usuario));
    }

    public void excluir(long idUsuario) {
        var usuario = repository.findById(idUsuario);
        usuario.ifPresent(repository::delete);
    }

}
