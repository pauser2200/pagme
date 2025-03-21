package com.br.pagme.usuario.conversoes;

import com.br.pagme.usuario.entidade.Usuario;
import com.br.pagme.usuario.resources.UsuarioResources;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UsuarioMapper {

    Usuario toEntity(UsuarioResources usuarioResources);

    UsuarioResources toDto(Usuario usuario);

    List<Usuario> toEntity(List<UsuarioResources> usuariosResource);

    List<UsuarioResources> toDto(List<Usuario> usuarios);

}
