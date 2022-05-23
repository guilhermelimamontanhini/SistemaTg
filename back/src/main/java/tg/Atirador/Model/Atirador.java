package tg.Atirador.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import tg.Atirador.FORM.NovoAtirador;

@Entity
@Table(name = "atirador")
public class Atirador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ra;
	
	@Size(max = 50)
	private String nome;
	
	@Size(max = 15)
	private String cpf;
	
	@Size(max = 15)
	@Column(name = "nomeguerra")
	private String nomeGuerra;
	
	@Size(max = 1)
	private String ocupacao;
	
	private Integer pontos;
	
	@Column(name = "quantidadeguardafimsemana")
	private Integer quantidadeGuardaFimSemana;
	
	@Column(name = "quantidadeguardadiasemana")
	private Integer quantidadeGuardaDiaSemana;
	
	@Column(name = "concluiutg")
	private Boolean concluiuTg;
	
	@Column(name = "realizoutiro")
	private Boolean realizouTiro;
	
	@Size(max = 1)
	private String destaque;
	
	@Column(name = "cursocabo")
	private Boolean cursoCabo;
	
	@Column(name = "numeropelotao")
	private Integer numeroPelotao;
	
	public Atirador() {
		
	}
	
	public Atirador(Long ra, @Size(max = 50) String nome, @Size(max = 15) String cpf, @Size(max = 15) String nomeGuerra,
			@Size(max = 1) String ocupacao, Integer pontos, Integer quantidadeGuardaFimSemana,
			Integer quantidadeGuardaDiaSemana, Boolean concluiuTg, Boolean realizouTiro,
			@Size(max = 20) String destaque, Boolean cursoCabo, Integer numeroPelotao) {
		this.ra = ra;
		this.nome = nome;
		this.cpf = cpf;
		this.nomeGuerra = nomeGuerra;
		this.ocupacao = ocupacao;
		this.pontos = pontos;
		this.quantidadeGuardaFimSemana = quantidadeGuardaFimSemana;
		this.quantidadeGuardaDiaSemana = quantidadeGuardaDiaSemana;
		this.concluiuTg = concluiuTg;
		this.realizouTiro = realizouTiro;
		this.destaque = destaque;
		this.cursoCabo = cursoCabo;
		this.numeroPelotao = numeroPelotao;
	}

	public Atirador(String nome, String cpf, NovoAtirador dadosNovoAtirador) {
		this.nome = nome;
		this.cpf = cpf;
		this.nomeGuerra = dadosNovoAtirador.getNomeGuerra();
		this.ocupacao = "A";
		this.pontos = 0;
		this.quantidadeGuardaFimSemana = 0;
		this.quantidadeGuardaDiaSemana = 0;
		this.concluiuTg = false;
		this.realizouTiro = false;
		this.destaque = "N";
		this.cursoCabo = false;
		this.numeroPelotao = 0;
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getQuantidadeGuardaFimSemana() {
		return quantidadeGuardaFimSemana;
	}

	public void setQuantidadeGuardaFimSemana(Integer quantidadeGuardaFimSemana) {
		this.quantidadeGuardaFimSemana = quantidadeGuardaFimSemana;
	}

	public Integer getQuantidadeGuardaDiaSemana() {
		return quantidadeGuardaDiaSemana;
	}

	public void setQuantidadeGuardaDiaSemana(Integer quantidadeGuardaDiaSemana) {
		this.quantidadeGuardaDiaSemana = quantidadeGuardaDiaSemana;
	}

	public Boolean getConcluiuTg() {
		return concluiuTg;
	}

	public void setConcluiuTg(Boolean concluiuTg) {
		this.concluiuTg = concluiuTg;
	}

	public Boolean getRealizouTiro() {
		return realizouTiro;
	}

	public void setRealizouTiro(Boolean realizouTiro) {
		this.realizouTiro = realizouTiro;
	}

	public String getDestaque() {
		return destaque;
	}

	public void setDestaque(String destaque) {
		this.destaque = destaque;
	}

	public Boolean getCursoCabo() {
		return cursoCabo;
	}

	public void setCursoCabo(Boolean cursoCabo) {
		this.cursoCabo = cursoCabo;
	}

	public Integer getNumeroPelotao() {
		return numeroPelotao;
	}

	public void setNumeroPelotao(Integer numeroPelotao) {
		this.numeroPelotao = numeroPelotao;
	}
	

}
