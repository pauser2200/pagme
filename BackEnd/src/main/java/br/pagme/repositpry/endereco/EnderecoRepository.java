package br.pagme.repositpry.endereco;

import br.pagme.domain.endereco.entidades.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
