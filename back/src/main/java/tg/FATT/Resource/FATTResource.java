package tg.FATT.Resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.FATT.DTO.FATTDTO;
import tg.FATT.FORM.AdicionarJustificativa;
import tg.FATT.Service.FATTService;

@RestController
@RequestMapping("/fatt")
public class FATTResource {
	
	@Autowired
	private FATTService fattService;
	
	/**
	 * 
	 * @description Método responsável por listar todos os atiradores
	 * @return ResponseEntity<List<AtiradorDTO>>
	 * */
	@GetMapping
	public ResponseEntity<List<FATTDTO>> listarTodosOsFATTs() {
		return this.fattService.listarTodosOsFATTs();
	}
	

	/**
	 * 
	 * @description Método para adiocionar ou atualizar justificativa do FATT
	 * @param <Long> idFatt
	 * @param <AdicionarJustificativa> adicionarJustificativa
	 * @return ResponseEntity<String>
	 * */
	@PutMapping("/justificativa/{id}")
	public ResponseEntity<String> adicionarJustificativa(@PathVariable Long id, 
			@RequestBody @Valid AdicionarJustificativa adicionarJustificativa) {
		return this.fattService.adicionarJustificativa(id, adicionarJustificativa);
	}

	/**
	 * 
	 * @description Método que aplica o FATT no atirador, se tiver uma justificativa valida ele só receberá os pontos base
	 * @param <Long> id
	 * @param <Boolean> justificado
	 * @return ResponseEntity<String>
	 * */
	@DeleteMapping("/aplicarfatt/{id}/{justificado}")
	ResponseEntity<String> aplicarFatt(@PathVariable Long id, @PathVariable Boolean justificado) {
		return this.fattService.aplicarFatt(id, justificado);
	}
	
}
