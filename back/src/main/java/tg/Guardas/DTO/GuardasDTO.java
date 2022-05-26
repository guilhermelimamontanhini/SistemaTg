package tg.Guardas.DTO;

import java.util.List;

import tg.Atirador.DTO.AtiradoresGuardaDTO;
import tg.Guardas.FORM.DadosDaGuarda;
import tg.Guardas.FORM.NovaGuarda;

public class GuardasDTO {
	
	private String tipoGuarda;
	
	private String dataGuarda;
	
	private List<AtiradoresGuardaDTO> listaIntegrantes;
	
	public GuardasDTO() {
		
	}

	public GuardasDTO(NovaGuarda dataTipoGuarda, List<AtiradoresGuardaDTO> todosOsIntegrantes) {
		this.tipoGuarda = dataTipoGuarda.getTipoGuarda();
		this.dataGuarda = dataTipoGuarda.getDataGuarda();
		this.listaIntegrantes = todosOsIntegrantes;
	}

	public String getTipoGuarda() {
		return tipoGuarda;
	}

	public String getDataGuarda() {
		return dataGuarda;
	}

	public List<AtiradoresGuardaDTO> getListaIntegrantes() {
		return listaIntegrantes;
	}

}
