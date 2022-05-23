package tg.FATT.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import tg.Auxiliares.OcorrenciaJustificativa.OcorrenciaJustificativa;

@Entity
@Table(name = "fatt")
public class FATT {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idFatt;
	
	@Column(name = "raatirador")
	private Long raAtirador;
	
	@Size(max = 15)
	@Column(name = "nomeguerra")
	private String nomeGuerra;
	
	private String ocorrencia;
	
	@Size(max=300)
	private String justificativa;
	
	public FATT() {
		
	}

	
	public FATT(Long raAtirador, String nomeGuerra, OcorrenciaJustificativa ocorrenciaJustificativa) {
		this.raAtirador = raAtirador;
		this.nomeGuerra = nomeGuerra;
		this.ocorrencia = ocorrenciaJustificativa.getOcorrencia();
		this.justificativa = ocorrenciaJustificativa.getJustificativa();
	}
	
	
	public Long getIdFatt() {
		return idFatt;
	}

	public void setIdFatt(Long idFatt) {
		this.idFatt = idFatt;
	}

	public Long getRaAtirador() {
		return raAtirador;
	}

	public void setRaAtirador(Long raAtirador) {
		this.raAtirador = raAtirador;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}


	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}


	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

}
