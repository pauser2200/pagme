package br.pagme.repositpry.divida;

import br.pagme.domain.divida.entidades.Divida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividaRepository extends JpaRepository<Divida, Long> {
}