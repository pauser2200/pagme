package com.br.pagme.parceladivida.repositorio;

import com.br.pagme.parceladivida.entidade.ParcelaDivida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PercelaDividaRepository extends JpaRepository<ParcelaDivida, Long> {
}
