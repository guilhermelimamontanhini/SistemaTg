package tg.Despensados.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dispensado")
public class Dispensado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long idDispensado;
	
	@Size(max = 50)
	private String nome;
	
	@Size(max = 15)
	private String cpf;
	
	@Size(max = 300)
	private String motivo;
	
	public Dispensado() {
		
	}
	
	public Dispensado(String nome, String cpf, String motivo) {
		this.nome = nome;
		this.cpf = cpf;
		this.motivo = motivo;
	}

	public Long getIdDispensado() {
		return idDispensado;
	}

	public void setIdDispensado(Long idDispensado) {
		this.idDispensado = idDispensado;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	

}
