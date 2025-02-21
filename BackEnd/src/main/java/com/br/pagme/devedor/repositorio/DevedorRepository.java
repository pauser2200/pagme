package com.br.pagme.devedor.repositorio;

import com.br.pagme.devedor.entidade.Devedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevedorRepository extends JpaRepository<Devedor, Long> {
}
