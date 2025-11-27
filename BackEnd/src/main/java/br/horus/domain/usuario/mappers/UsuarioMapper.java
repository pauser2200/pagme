package br.horus.domain.usuario.mappers;

import br.horus.controller.usuario.resources.UsuarioResources;
import br.horus.domain.usuario.entidades.Usuario;
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

    List<UsuarioResources> toDto(List<Usuario> usuarios);

}
