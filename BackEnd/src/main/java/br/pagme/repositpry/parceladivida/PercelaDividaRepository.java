package br.pagme.repositpry.parceladivida;

import br.pagme.domain.parceladivida.entidade.ParcelaDivida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PercelaDividaRepository extends JpaRepository<ParcelaDivida, Long> {
}
