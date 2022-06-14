package tg.Desligados.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Desligados.Model.Desligado;

public interface DesligadoRepository extends JpaRepository<Desligado, Long> {
	
	Optional<Desligado> findByIdDesligado(Long id);
	List<Desligado> findAllByOrderByNomeAsc();
	
}
