package tg.Alistados.Resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.Alistados.DTO.AlistadoDTO;
import tg.Alistados.FORM.AlistadoFORM;
import tg.Alistados.Service.AlistadoService;
import tg.Auxiliares.Motivo.MensagemMotivos;

@RestController
@RequestMapping("/alistado")
public class AlistadoResource {
	
	@Autowired
	private AlistadoService alistadoService;
	
	/**
	 * 
	 * @description Método responsável por listar todos os alistados
	 * @return ResponseEntity<List<AlistadoDTO>>
	 * */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<AlistadoDTO>> listarAlistados() {
		return alistadoService.listarAlistados();
	}
	
	/**
	 * 
	 * @description Método para atualizar um alistado
	 * @param <Long> id
	 * @param <AlistadoFORM> alistadoForm
	 * @return ResponseEntity<AlistadoDTO>
 	 * */
	@PutMapping("/atualizar/{id}") 
	public ResponseEntity<AlistadoDTO> atualizarAlistado(@PathVariable Long id, @RequestBody @Valid AlistadoFORM alistadoForm) {
		return this.alistadoService.atualizarAlitado(id, alistadoForm);
	}

	/**
	 * 
	 * @description Método para cadastrar um alistado
	 * @param <AlistadoFORM> alistadoForm
	 * @return ResponseEntity<String>
	 * */
	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarAlistado(@RequestBody @Valid AlistadoFORM alistadoForm) {
		return this.alistadoService.cadastrarAlistado(alistadoForm);
	}
	
	/**
	 * 
	 * @description Método responsavel por delatar o alistado da base de dados da Tabela Alistado e cadastrar esse dado na tabela de Desligados
	 * @param <Long> id
	 * @param <String> motivo
	 * @return ResponseEntity<String>
	 * */
	@DeleteMapping("/dispensar/{id}")
	public ResponseEntity<String> dispensarAlistado(@PathVariable Long id, @RequestBody @Valid MensagemMotivos mensagemMotivos) {
		return this.alistadoService.dispensarAlistado(id, mensagemMotivos);
	}
	
}
