package tg.Pelotao.DTO;

import java.util.List;
import java.util.Optional;

import tg.Atirador.Model.Atirador;
import tg.Pelotao.Model.Pelotao;

public class PelotaoDTO {
	
	private Integer numero;
	
	private String nome;
	
	private List<Atirador> atiradores;
	
	public PelotaoDTO() {
		
	}
	
	public PelotaoDTO(Optional<Pelotao> pelotao, List<Atirador> atiradores) {
		this.numero = pelotao.get().getNumero();
		this.nome = pelotao.get().getNome();
		this.atiradores = atiradores;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public List<Atirador> getAtiradores() {
		return atiradores;
	}
	
	

}
