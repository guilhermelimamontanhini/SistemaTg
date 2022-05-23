package tg.Atirador.Resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.Atirador.DTO.AtiradorDTO;
import tg.Atirador.FORM.AtualizarAtirador;
import tg.Atirador.FORM.NovoAtirador;
import tg.Atirador.Service.AtiradorService;
import tg.Auxiliares.Motivo.MensagemMotivos;
import tg.Auxiliares.OcorrenciaJustificativa.OcorrenciaJustificativa;

@RestController
@RequestMapping("/atirador")
public class AtiradorResource {
	
	@Autowired
	private AtiradorService atiradorService;
	
	/**
	 * 
	 * @description Método responsável por cadastrar um novo atirador
	 * @param <Long> id
	 * @param <NovoAtirador> atirador
	 * */
	@PostMapping("/cadastrar/{id}")
	public ResponseEntity<String> cadastrarAtiradorResource(@PathVariable Long id, @RequestBody @Valid NovoAtirador dadosNovoAtirador) {
		return this.atiradorService.cadastrarAtiradorService(id, dadosNovoAtirador);
	}
	
	/**
	 * 
	 * @description Método responsável por listar todos os atiradores
	 * @return ResponseEntity<List<AtiradorDTO>>
	 * */
	@GetMapping
	public ResponseEntity<List<AtiradorDTO>> listarTodosAtiradoresResource() {
		return this.atiradorService.listarTodosOsAtiradores();
	}
	
	/**
	 * 
	 * @description Método responsável por atualizar o Atirador
	 * @param <Long> id
	 * @param <AtualizarAtirador> atualizarAtirador
	 * @return ResponseEntity<String>
	 */
	@PutMapping("/atualizar/{ra}")
	public ResponseEntity<String> atualizarDadosAtiradorService(@PathVariable Long ra,
			@RequestBody @Valid AtualizarAtirador atualizarAtirador) {
		return this.atiradorService.atualizarDadosAtiradorService(ra, atualizarAtirador);
	}
	
	/**
	 * 
	 * @description Método responsável para alterar a ocupaçao de um Atirador
	 * @param <Long> ra
	 * @return ResponseEntity<String>
	 */
	@PutMapping("/ocupacao/{ra}")
	public ResponseEntity<String> promoverAtiradorParaAlunoResource(@PathVariable Long ra) {
		return this.atiradorService.promoverAtiradorParaAlunoService(ra);
	}

	/**
	 * 
	 * @description Método responsável para adicionar um FATT no Atirador
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 */
	@PutMapping("/fatt/{ra}")
	public ResponseEntity<String> adicionarFATT(@PathVariable Long ra, @RequestBody @Valid OcorrenciaJustificativa ocorrenciaJustificativa) {
		return this.atiradorService.adicionarFATT(ra, ocorrenciaJustificativa);
	}
	
	/**
	 * 
	 * @description Método para desligar um atirador e adiciona-lo a tabela de Desligados
	 * @param <Long> id
	 * @param <MensagemMotivos> motivoFORM
	 * @return ResponseEntity<String>
	 * */
	@DeleteMapping("/desligar/{id}")
	public ResponseEntity<String> desligarAtiradorResource(@PathVariable Long id, @RequestBody @Valid MensagemMotivos mensagemMotivos) {
		return this.atiradorService.desligarAtiradorService(id, mensagemMotivos);
	}
}
