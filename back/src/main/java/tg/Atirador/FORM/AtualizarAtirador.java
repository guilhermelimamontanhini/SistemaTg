package tg.Atirador.FORM;

import java.util.Optional;

import javax.validation.constraints.Size;

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;

public class AtualizarAtirador {
	
	@Size(max = 15)
	private String nomeGuerra;
	
	private Integer pontos;
	
	private Integer quantidadeGuardaFimSemana;
	
	private Integer quantidadeGuardaDiaSemana;
	
	private Boolean concluiuTg;
	
	private Boolean realizouTiro;
	
	@Size(max = 1)
	private String destaque;
	
	private Boolean cursoCabo;
	
	public AtualizarAtirador() {
		
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getQuantidadeGuardaFimSemana() {
		return quantidadeGuardaFimSemana;
	}

	public void setQuantidadeGuardaFimSemana(Integer quantidadeGuardaFimSemana) {
		this.quantidadeGuardaFimSemana = quantidadeGuardaFimSemana;
	}

	public Integer getQuantidadeGuardaDiaSemana() {
		return quantidadeGuardaDiaSemana;
	}

	public void setQuantidadeGuardaDiaSemana(Integer quantidadeGuardaDiaSemana) {
		this.quantidadeGuardaDiaSemana = quantidadeGuardaDiaSemana;
	}

	public Boolean getConcluiuTg() {
		return concluiuTg;
	}

	public void setConcluiuTg(Boolean concluiuTg) {
		this.concluiuTg = concluiuTg;
	}

	public Boolean getRealizouTiro() {
		return realizouTiro;
	}

	public void setRealizouTiro(Boolean realizouTiro) {
		this.realizouTiro = realizouTiro;
	}

	public String getDestaque() {
		return destaque;
	}

	public void setDestaque(String destaque) {
		this.destaque = destaque;
	}

	public Boolean getCursoCabo() {
		return cursoCabo;
	}

	public void setCursoCabo(Boolean cursoCabo) {
		this.cursoCabo = cursoCabo;
	}
	
	public Atirador atualizarDadosDoAtirador(Long ra, AtiradorRepository atiradorRepository) {
		// Chamando o registro antigo
		Optional<Atirador> atirador = atiradorRepository.findByRa(ra);
		
		atirador.get().setNomeGuerra(nomeGuerra);
		atirador.get().setPontos(pontos);
		atirador.get().setQuantidadeGuardaFimSemana(quantidadeGuardaFimSemana);
		atirador.get().setQuantidadeGuardaDiaSemana(quantidadeGuardaDiaSemana);
		atirador.get().setConcluiuTg(concluiuTg);
		atirador.get().setRealizouTiro(realizouTiro);		
		atirador.get().setDestaque(destaque);
		atirador.get().setCursoCabo(cursoCabo);
		
		if(atirador.get().getCursoCabo()) {
			atirador.get().setOcupacao("M");
		}
		
		return atirador.get();
	}

}
