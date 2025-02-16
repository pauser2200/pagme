package com.br.pagme.divida.repositorio;

import com.br.pagme.divida.entidade.Divida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividaRepository extends JpaRepository<Divida, Long> {
}
