package tg.FATT.DTO;

import java.util.List;
import java.util.stream.Collectors;

import tg.FATT.Model.FATT;

public class FATTDTO {
	
	private Long idFatd;
	
	private Long raAtirador;

	private String nomeGuerra;
	
	private String ocorrencia;
	
	private String justificativa;
	
	public FATTDTO() {
		
	}
	
	public FATTDTO(FATT fatt) {
		this.idFatd = fatt.getIdFatt();
		this.raAtirador = fatt.getRaAtirador();
		this.nomeGuerra = fatt.getNomeGuerra();
		this.ocorrencia = fatt.getOcorrencia();
		this.justificativa = fatt.getJustificativa();
	}

	
	public Long getIdFatd() {
		return idFatd;
	}

	public Long getRaAtirador() {
		return raAtirador;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public String getJustificativa() {
		return justificativa;
	}


	public static List<FATTDTO> converterModelParaDTO(List<FATT> fatt){
		return fatt.stream().map(FATTDTO::new).collect(Collectors.toList());
	}
	
	
	
}
