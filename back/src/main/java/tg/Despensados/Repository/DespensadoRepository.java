package tg.Despensados.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Despensados.Model.Despensado;

public interface DespensadoRepository extends JpaRepository<Despensado, Long> {
	
	Optional<Despensado> findByIdDispensado(Long id);
	List<Despensado> findAllByOrderByNomeAsc();

}
