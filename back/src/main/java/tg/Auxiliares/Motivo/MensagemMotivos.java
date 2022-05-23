package tg.Auxiliares.Motivo;

import javax.validation.constraints.Size;

public class MensagemMotivos {
	
	@Size(max = 300)
	private String motivo;

	public MensagemMotivos() {
		
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
