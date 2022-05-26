package tg.Guardas.FORM;

import javax.validation.constraints.Size;

public class NovaGuarda {
	
	@Size(max=1)
	private String tipoGuarda;
	
	private String dataGuarda;
	
	public NovaGuarda() {
		
	}

	public String getTipoGuarda() {
		return tipoGuarda;
	}

	public void setTipoGuarda(String tipoGuarda) {
		this.tipoGuarda = tipoGuarda;
	}

	public String getDataGuarda() {
		return dataGuarda;
	}

	public void setDataGuarda(String dataGuarda) {
		this.dataGuarda = dataGuarda;
	}
	

}
