package br.pagme.domain.usuario.mappers;

import br.pagme.controller.usuario.resources.UsuarioResources;
import br.pagme.domain.usuario.entidades.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UsuarioMapper {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Mapping(target ="password", expression = "java(this.passwordEncoder.encode(usuarioResources.password()))")
    public abstract Usuario toEntity(UsuarioResources usuarioResources);

    @Mapping(target = "password",  ignore = true)
    public abstract UsuarioResources toDto(Usuario usuario);

    public abstract List<UsuarioResources> toDto(List<Usuario> usuarios);

}
