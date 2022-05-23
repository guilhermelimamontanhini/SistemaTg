package tg.Alistados.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import tg.Alistados.FORM.AlistadoFORM;

@Entity
@Table(name = "alistado")
public class Alistado {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idAlistado;
	
	@Size(max = 50)
	private String nome;
	
	@Size(max = 15)
	private String cpf;
	
	@Size(max = 1)
	private String refratario;
	
	public Alistado() {
		
	}
	
	public Alistado(Long idAlistado, @Size(max = 50) String nome, @Size(max = 15) String cpf,
			@Size(max = 1) String refratario) {
		this.idAlistado = idAlistado;
		this.nome = nome;
		this.cpf = cpf;
		this.refratario = refratario;
	}



	public Alistado(AlistadoFORM alistadoForm) {
		this.nome = alistadoForm.getNome();
		this.cpf = alistadoForm.getCpf();
		this.refratario = alistadoForm.getRefratario();
	}
	
	public Long getIdAlistado() {
		return idAlistado;
	}

	public void setIdAlistado(Long idAlistado) {
		this.idAlistado = idAlistado;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRefratario() {
		return refratario;
	}
	public void setRefratario(String refratario) {
		this.refratario = refratario;
	}
	

}
