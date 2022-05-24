package tg.Pelotao.Resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.Pelotao.DTO.PelotaoDTO;
import tg.Pelotao.FORM.NovoPelotao;
import tg.Pelotao.Service.PelotaoService;

@RestController
@RequestMapping("/pelotao")
public class PelotaoResource {
	
	@Autowired
	private PelotaoService pelotaoService;
	
	/**
	 * 
	 * @description Método responsável por cadastrar os pelotões, quando houver 4 pelotões, os atiradores serão cadastrados nos 
	 * mesmos
	 * @param <NovoPelotao> novoPelotao
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarPelotao(@RequestBody @Valid NovoPelotao novoPelotao) {
		return this.pelotaoService.cadastrarPelotao(novoPelotao);
	}

	/**
	 * 
	 * @description Método responsável por listar todos os pelotões e seus integrantes
	 * @return ResponseEntity<List<PelotaoDTO>>
	 * */
	@GetMapping
	public ResponseEntity<List<PelotaoDTO>> listarTodosOsPelotoes() {
		return this.pelotaoService.listarTodosOsPelotoes();
	}
	
}
