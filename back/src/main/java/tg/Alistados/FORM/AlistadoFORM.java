package tg.Alistados.FORM;

import java.util.Optional;

import javax.validation.constraints.Size;

import tg.Alistados.Model.Alistado;
import tg.Alistados.Repository.AlistadoRepository;

public class AlistadoFORM {
	
	@Size(max = 50)
	private String nome;
	
	@Size(max = 15)
	private String cpf;
	
	@Size(max = 1)
	private String refratario;
	
	public AlistadoFORM() {
		
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
	
	public Alistado atualizarDadosDoAlistado(Long id, AlistadoRepository alistadosRepository) {
		// Chamando o registro antigo
		Optional<Alistado> alistado = alistadosRepository.findById(id);
		
		alistado.get().setNome(nome);
		alistado.get().setCpf(cpf);
		alistado.get().setRefratario(refratario);
		
		return alistado.get();
	}

}
