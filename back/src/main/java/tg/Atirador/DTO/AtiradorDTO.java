package tg.Atirador.DTO;

import java.util.List;
import java.util.stream.Collectors;

import tg.Atirador.Model.Atirador;

public class AtiradorDTO {
	
	private Long ra;
	
	private String nome;
	
	private String cpf;
	
	private String nomeGuerra;
	
	private String ocupacao;
	
	private Integer pontos;
	
	private Integer quantidadeGuardaFimSemana;
	
	private Integer quantidadeGuardaDiaSemana;
	
	private Boolean concluiuTg;
	
	private Boolean realizouTiro;
	
	private String destaque;
	
	private Boolean cursoCabo;
	
	private Integer numeroPelotao;
	
	public AtiradorDTO() {
		
	}
	
	public AtiradorDTO(Atirador atirador) {
		this.ra = atirador.getRa();
		this.nome = atirador.getNome();
		this.cpf = atirador.getCpf();
		this.nomeGuerra = atirador.getNomeGuerra();
		this.ocupacao = atirador.getOcupacao();
		this.pontos = atirador.getPontos();
		this.quantidadeGuardaFimSemana = atirador.getQuantidadeGuardaFimSemana();
		this.quantidadeGuardaDiaSemana = atirador.getQuantidadeGuardaDiaSemana();
		this.concluiuTg = atirador.getConcluiuTg();
		this.realizouTiro = atirador.getRealizouTiro();
		this.destaque = atirador.getDestaque();
		this.cursoCabo = atirador.getCursoCabo();
		this.numeroPelotao = atirador.getNumeroPelotao();
	}
	
	public Long getRa() {
		return ra;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public Integer getPontos() {
		return pontos;
	}

	public Integer getQuantidadeGuardaFimSemana() {
		return quantidadeGuardaFimSemana;
	}

	public Integer getQuantidadeGuardaDiaSemana() {
		return quantidadeGuardaDiaSemana;
	}

	public Boolean getConcluiuTg() {
		return concluiuTg;
	}

	public Boolean getRealizouTiro() {
		return realizouTiro;
	}

	public String getDestaque() {
		return destaque;
	}

	public Boolean getCursoCabo() {
		return cursoCabo;
	}

	public Integer getNumeroPelotao() {
		return numeroPelotao;
	}
	
	public static List<AtiradorDTO> converterModelParaDTO(List<Atirador> atirador){
		return atirador.stream().map(AtiradorDTO::new).collect(Collectors.toList());
	}

}
