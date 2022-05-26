package tg.Atirador.FORM;

import java.util.Optional;

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;

public class AdicionarPontuacaoGuarda {
	
	public Atirador atualizarPontosDaGuardaAtirador(Long ra, String tipoGuarda, AtiradorRepository atiradorRepository) {
		// Chamando o registro antigo
		Optional<Atirador> atirador = atiradorRepository.findByRa(ra);
		
		if(tipoGuarda.equals("F")) {
			atirador.get().setQuantidadeGuardaFimSemana(atirador.get().getQuantidadeGuardaFimSemana()+1);
		}
		if(tipoGuarda.equals("D")) {
			atirador.get().setQuantidadeGuardaDiaSemana(atirador.get().getQuantidadeGuardaDiaSemana()+1);
		}
		
		return atirador.get();
	}

}
