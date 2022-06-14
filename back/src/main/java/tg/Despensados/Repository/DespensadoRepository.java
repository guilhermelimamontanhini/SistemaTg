package tg.Despensados.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Despensados.Model.Dispensado;

public interface DispensadoRepository extends JpaRepository<Dispensado, Long> {
	
	Optional<Dispensado> findByIdDispensado(Long id);

}
