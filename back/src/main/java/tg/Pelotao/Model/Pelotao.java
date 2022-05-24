package tg.Pelotao.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import tg.Pelotao.FORM.NovoPelotao;

@Entity
@Table(name = "pelotao")
public class Pelotao {
	
	@Id
	private Integer numero;
	
	@Size(max = 50)
	private String nome;
	
	public Pelotao() {
		
	}
	
	public Pelotao(NovoPelotao novoPelotao) {
		this.numero = novoPelotao.getNumero();
		this.nome = novoPelotao.getNome();
	}

	public Pelotao(Integer numero, @Size(max = 50) String nome) {
		this.numero = numero;
		this.nome = nome;
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
