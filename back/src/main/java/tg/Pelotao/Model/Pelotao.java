package tg.Pelotao.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pelotao")
public class Pelotao {
	
	@Id
	@Size(max = 1)
	private Long numero;
	
	@Size(max = 50)
	private String nome;
	
	public Pelotao() {
		
	}

	public Pelotao(@Size(max = 1) Long numero, @Size(max = 50) String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
