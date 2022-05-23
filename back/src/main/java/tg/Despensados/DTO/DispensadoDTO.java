package tg.Despensados.DTO;

import java.util.List;
import java.util.stream.Collectors;

import tg.Despensados.Model.Dispensado;

public class DispensadoDTO {
	
	private Long idDispensado;
	
	private String nome;
	
	private String cpf;
	
	private String motivo;
	
	public DispensadoDTO() {
		
	}
	
	public DispensadoDTO(Dispensado dispensado) {
		this.idDispensado = dispensado.getIdDispensado();
		this.nome = dispensado.getNome();
		this.cpf = dispensado.getCpf();
		this.motivo = dispensado.getMotivo();
	}

	public Long getIdDispensado() {
		return idDispensado;
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
	
	public static List<DispensadoDTO> converterModelParaDTO(List<Dispensado> despensado){
		return despensado.stream().map(DispensadoDTO::new).collect(Collectors.toList());
	}


}
