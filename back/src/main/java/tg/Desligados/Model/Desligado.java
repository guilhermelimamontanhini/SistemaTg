package tg.Desligados.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "desligado")
public class Desligado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long idDesligado;
	
	@Size(max = 50)
	private String nome;
	
	@Size(max = 15)
	private String cpf;
	
	@Size(max = 300)
	private String motivo;
	
    public Desligado() {
    	
    }
    
    public Desligado(String nome, String cpf, String motivo) {
		this.nome = nome;
		this.cpf = cpf;
		this.motivo = motivo;
	}


	public Long getIdDesligado() {
		return idDesligado;
	}

	public void setIdDesligado(Long idDesligado) {
		this.idDesligado = idDesligado;
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
