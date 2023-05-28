package tg.Atirador.FORM;

import java.util.Optional;

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;

public class AlterarOcupacao {

	public Atirador atualizarDadosDoAtirador(Long ra, Boolean promover, AtiradorRepository atiradorRepository) {
		// Chamando o registro antigo
		Optional<Atirador> atirador = atiradorRepository.findByRa(ra);
		
		if(atirador.get().getOcupacao().equals("C")) {
			if (promover) {
				atirador.get().setOcupacao("M");
			} else {
				atirador.get().setOcupacao("A");
			}
			
		} else {
			atirador.get().setOcupacao("C");
			
		}
		
		return atirador.get();
	}

}
