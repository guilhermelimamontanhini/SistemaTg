package tg.Pelotao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Pelotao.Model.Pelotao;

public interface PelotaoRepository extends JpaRepository<Pelotao, Integer> {
	
	Optional<Pelotao> findByNumero(Integer numero);
	Optional<Pelotao> findByNome(String nome);

}
