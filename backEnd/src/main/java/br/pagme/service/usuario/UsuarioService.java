package br.pagme.service.usuario;

import br.pagme.controller.usuario.resources.UsuarioResources;
import br.pagme.domain.usuario.entidades.Usuario;
import br.pagme.domain.usuario.mappers.UsuarioMapper;
import br.pagme.repositpry.usuario.UsuarioRepository;
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
