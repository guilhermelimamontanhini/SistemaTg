package tg.Desligados.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.Desligados.DTO.DesligadoDTO;
import tg.Desligados.Service.DesligadoService;

@RestController
@RequestMapping("/desligado")
public class DesligadoResource {
	
	@Autowired
	private DesligadoService desligadoService;
	
	/**
	 * 
	 * @description Método responsavél por listar todos os Despensados
	 * @return ResponseEntity<List<DespensadoDTO>>
	 * */
	@GetMapping
	public ResponseEntity<List<DesligadoDTO>> listarDesligados()  {
		return this.desligadoService.listarDesligados();
	}

	/**
	 * 
	 * @description Método responsavél por deletar permanentemente o despensado da base de dados
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 * */
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletarDesligadoService(@PathVariable Long id) {
		return this.desligadoService.deletarDesligadoService(id);
	}
	

}
