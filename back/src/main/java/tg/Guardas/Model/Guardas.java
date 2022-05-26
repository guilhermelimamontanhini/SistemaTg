package tg.Guardas.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import tg.Guardas.FORM.NovaGuarda;

@Entity
@Table(name = "guardas")
public class Guardas {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idGuarda;
	
	@Size(max=1)
	@Column(name = "tipoguarda")
	private String tipoGuarda;
	
	@Column(name = "dataguarda")
	private String dataGuarda;
	
	@Size(max = 15)
	@Column(name = "integrante")
	private String nomeGuerraIntegrante;

	public Guardas() {

	}
	
	public Guardas(@Valid NovaGuarda novaGuarda, String nomeGuerra) {
		this.tipoGuarda = novaGuarda.getTipoGuarda();
		this.dataGuarda = novaGuarda.getDataGuarda();
		this.nomeGuerraIntegrante = nomeGuerra;
	}

	public Long getIdGuarda() {
		return idGuarda;
	}

	public void setIdGuarda(Long idGuarda) {
		this.idGuarda = idGuarda;
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

	public String getNomeGuerraIntegrante() {
		return nomeGuerraIntegrante;
	}

	public void setNomeGuerraIntegrante(String nomeGuerraIntegrante) {
		this.nomeGuerraIntegrante = nomeGuerraIntegrante;
	}
	

}
