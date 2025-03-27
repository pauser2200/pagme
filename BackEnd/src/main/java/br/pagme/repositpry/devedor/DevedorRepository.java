package br.pagme.repositpry.devedor;

import br.pagme.domain.devedor.entidades.Devedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevedorRepository extends JpaRepository<Devedor, Long> {
}
