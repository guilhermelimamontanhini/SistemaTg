package tg.Pelotao.FORM;

import javax.validation.constraints.Size;

public class NovoPelotao {
	
	private Integer numero;
	
	@Size(max = 50)
	private String nome;
	
	public NovoPelotao() {
		
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
