package tg.Atirador.DTO;

public class AtiradoresGuardaDTO {

	private Long ra;
	
	private String nomeGuerra;
	
	private String ocupacao;

	public AtiradoresGuardaDTO(Long ra, String nomeGuerra, String ocupacao) {
		this.ra = ra;
		this.nomeGuerra = nomeGuerra;
		this.ocupacao = ocupacao;
	}

	public Long getRa() {
		return ra;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public String getOcupacao() {
		return ocupacao;
	}
	
	
	
}
