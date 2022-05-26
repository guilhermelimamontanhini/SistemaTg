package tg.Guardas.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.Guardas.Model.Guardas;

public interface GuardasRepository extends JpaRepository<Guardas, Long> {
	
	Optional<Guardas> findByDataGuarda(String dataGuarda);
	Optional<Guardas> findByNomeGuerraIntegranteAndDataGuarda(String nomeGuerraIntegranteAtual, String dataGuarda);
	List<Guardas> findAllByDataGuarda(String dataGuarda);

}
