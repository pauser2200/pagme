package br.pagme.service.usuario;

import br.pagme.controller.usuario.resources.UsuarioResources;
import br.pagme.domain.usuario.entidades.Usuario;
import br.pagme.domain.usuario.mappers.UsuarioMapper;
import br.pagme.enums.StatusEnum;
import br.pagme.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    protected final UsuarioMapper mapper;

    public List<UsuarioResources> listar() {
        List<Usuario> usuarios = repository.findAll();
        return this.mapper.toDto(usuarios);
    }

    public UsuarioResources salvar(UsuarioResources resources) {
       AtomicReference<Usuario> usuarionovo = new AtomicReference<>(new Usuario());
      repository.findByEmail(resources.email()).ifPresentOrElse(
                (usu) -> {
                    usu.setStatus(StatusEnum.ATIVO);
                    repository.save(usu);
                },
                () -> {
                    usuarionovo.set(this.mapper.toEntity(resources));
                    usuarionovo.get().setStatus(StatusEnum.ATIVO);
                    this.repository.save(usuarionovo.get());

                }
        );

        return this.mapper.toDto(usuarionovo.get());
    }

    public void excluir(long idUsuario) {
        var usuario = repository.findById(idUsuario);
        usuario.ifPresent(usu -> {
            usu.setStatus(StatusEnum.INATIVO);
            this.repository.save(usu);
        });
    }

}
