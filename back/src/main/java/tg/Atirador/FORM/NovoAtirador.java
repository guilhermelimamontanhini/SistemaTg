package tg.Atirador.FORM;

import javax.validation.constraints.Size;

public class NovoAtirador {
	
	@Size(max = 15)
	private String nomeGuerra;
	
	
	public NovoAtirador() {
		
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}


}
