package br.pagme.repository.divida;

import br.pagme.domain.divida.entidades.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Long> {

     List<Divida> findByDevedorId(@PathVariable("devedorId") Long devedorId);

}
