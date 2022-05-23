package tg.Alistados.DTO;

import java.util.List;
import java.util.stream.Collectors;

import tg.Alistados.Model.Alistado;

public class AlistadoDTO {
	
	private Long idAlistado;
	
	private String nome;
	
	private String cpf;
	
	private String refratario;
	
	public AlistadoDTO() {
		
	}
	
	public AlistadoDTO(Alistado alistado) {
		this.idAlistado = alistado.getIdAlistado();
		this.nome = alistado.getNome();
		this.cpf = alistado.getCpf();
		this.refratario = alistado.getRefratario();
	}

	public Long getIdAlistado() {
		return idAlistado;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String getRefratario() {
		return refratario;
	}

	public static List<AlistadoDTO> converterModelParaDTO(List<Alistado> alistados){
		return alistados.stream().map(AlistadoDTO::new).collect(Collectors.toList());
	}

}
