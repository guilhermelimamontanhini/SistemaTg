package tg.Alistados.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Alistados.Model.Alistado;

public interface AlistadoRepository extends JpaRepository<Alistado, Long> {

	Optional<Alistado> findByIdAlistado(Long id);
	Optional<Alistado> findByNome(String nome);
	Optional<Alistado> findByCpf(String cpf);

}
