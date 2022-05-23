package tg.FATT.FORM;

import java.util.Optional;

import javax.validation.constraints.Size;

import tg.FATT.Model.FATT;
import tg.FATT.Repository.FATTRepository;

public class AdicionarJustificativa {
	
	@Size(max=300)
	private String justificativa;
	
	public AdicionarJustificativa() {
		
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public FATT atualizarJustificativa(Long id, FATTRepository fattRepository) {
		
		Optional<FATT> fatt = fattRepository.findByIdFatt(id);
		
		fatt.get().setJustificativa(justificativa);
		
		return fatt.get();
		
	}

}
