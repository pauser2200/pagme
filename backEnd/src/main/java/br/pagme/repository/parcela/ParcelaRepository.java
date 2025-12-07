package br.pagme.repository.parcela;

import br.pagme.domain.parcela.entidades.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
}
