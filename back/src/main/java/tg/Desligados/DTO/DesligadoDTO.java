package tg.Desligados.DTO;

import java.util.List;
import java.util.stream.Collectors;

import tg.Desligados.Model.Desligado;

public class DesligadoDTO {
	
	private Long idDesligado;
	
	private String nome;
	
	private String cpf;
	
	private String motivo;
	
	public DesligadoDTO() {
		
	}
	
	public DesligadoDTO(Desligado desligado) {
		this.idDesligado = desligado.getIdDesligado();
		this.nome = desligado.getNome();
		this.cpf = desligado.getCpf();
		this.motivo = desligado.getMotivo();
	}

	public Long getIdDesligado() {
		return idDesligado;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getMotivo() {
		return motivo;
	}
	
	public static List<DesligadoDTO> converterModelParaDTO(List<Desligado> desligado){
		return desligado.stream().map(DesligadoDTO::new).collect(Collectors.toList());
	}

}
