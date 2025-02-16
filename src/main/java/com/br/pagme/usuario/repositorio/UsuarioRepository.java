package com.br.pagme.usuario.repositorio;

import com.br.pagme.usuario.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
