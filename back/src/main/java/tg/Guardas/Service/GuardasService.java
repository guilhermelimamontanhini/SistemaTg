package tg.Guardas.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tg.Atirador.DTO.AtiradoresGuardaDTO;
import tg.Atirador.FORM.AdicionarPontuacaoGuarda;
import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.Guardas.DTO.GuardasDTO;
import tg.Guardas.FORM.DataGuarda;
import tg.Guardas.FORM.NovaGuarda;
import tg.Guardas.Model.Guardas;
import tg.Guardas.Repository.GuardasRepository;

@Service
public class GuardasService {
	
	@Autowired
	private GuardasRepository guardasRepository;
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	/**
	 * 
	 * @description Método para adicionar integrantes na guarda
	 * @param <Long> ra
	 * @param <NovaGuarda> novaGuarda
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> adicionarIntegranteGuarda(@PathVariable Long ra, 
			@RequestBody @Valid NovaGuarda novaGuarda) {
		
		String mensagem = "";
		boolean valido = false;
		
		AdicionarPontuacaoGuarda adicionarPontuacaoGuarda = new AdicionarPontuacaoGuarda();
		Atirador atirador = adicionarPontuacaoGuarda.atualizarPontosDaGuardaAtirador(ra,novaGuarda.getTipoGuarda(),
				atiradorRepository);
		
		try {
			Guardas adicionarNovaGuarda = new Guardas(novaGuarda,atirador.getNomeGuerra());
			this.guardasRepository.save(adicionarNovaGuarda);
			valido = true;
			mensagem = "Integrante da guarda adicionado cadastrado com sucesso";
		} catch(Exception e) {
			e.printStackTrace();
			mensagem = "Erro ao cadastrar nova guarda.";
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(mensagem);
		}
		
		return ResponseEntity
				.status(valido ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
				.body(mensagem);
		
	}
	
	/**
	 * 
	 * @description Método para listar as guardas por dia 
	 * @param <DataGuarda> data
	 * @return ResponseEntity<GuardasDTO>
	 */
	public ResponseEntity<GuardasDTO> listarGuardaDoDia(@RequestBody @Valid DataGuarda data) {
		
		List<Guardas> guardaDia = this.guardasRepository.findAllByDataGuarda(data.getDataGuarda());
		List<AtiradoresGuardaDTO> todosOsIntegrantes = new ArrayList<>();
		NovaGuarda dataTipoGuarda = new NovaGuarda();
		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
		
		for(int cont = 0; cont <= guardaDia.size()-1; cont++) {
			Date dataAntiga = null;
			try {
				dataAntiga = formatoEntrada.parse(guardaDia.get(cont).getDataGuarda());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataTipoGuarda.setTipoGuarda(guardaDia.get(cont).getTipoGuarda());
			dataTipoGuarda.setDataGuarda(formatoSaida.format(dataAntiga));
			Optional<Atirador> atirador = this.atiradorRepository.findByNomeGuerra(guardaDia.get(cont).getNomeGuerraIntegrante());
			AtiradoresGuardaDTO integranteGuarda = new AtiradoresGuardaDTO(atirador.get().getRa(), 
					atirador.get().getNomeGuerra(), atirador.get().getOcupacao());
			todosOsIntegrantes.add(integranteGuarda);
		}
		
		GuardasDTO guardaIntegrantes = new GuardasDTO(dataTipoGuarda,todosOsIntegrantes);
		return ResponseEntity.ok(guardaIntegrantes); 
		
	}
	

}
