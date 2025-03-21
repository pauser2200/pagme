package com.br.pagme.usuario.servico;

import com.br.pagme.usuario.conversoes.UsuarioMapper;
import com.br.pagme.usuario.entidade.Usuario;
import com.br.pagme.usuario.repositorio.UsuarioRepository;
import com.br.pagme.usuario.resources.UsuarioResources;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    public List<UsuarioResources> listar() {
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = repository.findAll();
        return this.mapper.toDto(usuarios);
    }

    public UsuarioResources salvar(UsuarioResources resources) {
        var usuario = this.mapper.toEntity(resources);
        return this.mapper.toDto(this.repository.save(usuario));
    }

}
