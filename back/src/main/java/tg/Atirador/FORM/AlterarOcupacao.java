package tg.Atirador.FORM;

import java.util.Optional;

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;

public class AlterarOcupacao {

	public Atirador atualizarDadosDoAtirador(Long ra, AtiradorRepository atiradorRepository) {
		// Chamando o registro antigo
		Optional<Atirador> atirador = atiradorRepository.findByRa(ra);
		
		if(atirador.get().getOcupacao().equals("A")) {
			atirador.get().setOcupacao("C");
		} else {
			atirador.get().setOcupacao("A");
		}
		
		return atirador.get();
	}

}
