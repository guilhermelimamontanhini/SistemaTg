package tg.Atirador.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Atirador.Model.Atirador;

public interface AtiradorRepository extends JpaRepository<Atirador, Long> {
	
	Optional<Atirador> findByRa(Long ra);
	Optional<Atirador> findByNomeGuerra(String nomeGuerra);
	List<Atirador> findAllByOrderByNomeAsc();
	List<Atirador> findAllByNumeroPelotaoOrderByNomeAsc(Integer numeroPelotao);
	
	               
}
