package tg.Guardas.FORM;

public class DadosDaGuarda {
	
	
	private String nomeGuerraIntegranteAtual;
	
	private String nomeGuerraIntegranteNovo;
	
	private String dataGuarda;
	
	public DadosDaGuarda() {
		
	}
	

	public String getNomeGuerraIntegranteAtual() {
		return nomeGuerraIntegranteAtual;
	}


	public void setNomeGuerraIntegranteAtual(String nomeGuerraIntegranteAtual) {
		this.nomeGuerraIntegranteAtual = nomeGuerraIntegranteAtual;
	}


	public String getNomeGuerraIntegranteNovo() {
		return nomeGuerraIntegranteNovo;
	}


	public void setNomeGuerraIntegranteNovo(String nomeGuerraIntegranteNovo) {
		this.nomeGuerraIntegranteNovo = nomeGuerraIntegranteNovo;
	}


	public String getDataGuarda() {
		return dataGuarda;
	}


	public void setDataGuarda(String dataGuarda) {
		this.dataGuarda = dataGuarda;
	}
	
	//public void atualizarDadosDaGuarda(@Valid DadosDaGuarda dadosDaGuarda, GuardasRepository guardasRepository, 
	//		AtiradorRepository atiradotRepository) {
		
	//	Optional<Guardas> guarda = guardasRepository.findByNomeGuerraIntegranteAndDataGuarda
	//			(dadosDaGuarda.getNomeGuerraIntegranteAtual(), dadosDaGuarda.getDataGuarda());
		
		//Optional<Atirador> atiradorAtual = atiradorRepository.findByNomeGuerra(dadosDaGuarda.getNomeGuerraIntegranteAtual());
		//Optional<Atirador> atiradorNovo = atiradorRepository.findByNomeGuerra(dadosDaGuarda.getNomeGuerraIntegranteNovo());
		
		
	//}


}
