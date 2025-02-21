package com.br.pagme.endereco.repositorio;

import com.br.pagme.endereco.entidade.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
