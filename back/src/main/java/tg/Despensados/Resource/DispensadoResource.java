package tg.Despensados.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.Despensados.DTO.DispensadoDTO;
import tg.Despensados.Service.DispensadoService;

@RestController
@RequestMapping("/dispensado")
public class DispensadoResource {
	
	@Autowired
	private DispensadoService dispensadoService;
	
	/**
	 * 
	 * @description Método responsavél por listar todos os Dispensados
	 * @return ResponseEntity<List<DespensadoDTO>>
	 * */
	@GetMapping
	public ResponseEntity<List<DispensadoDTO>> listarDispensados() {
		return this.dispensadoService.listarDispensados();
	}

	/**
	 * 
	 * @description Método responsavél por deletar permanentemente o dispensado da base de dados
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 * */
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletarDespensado(@PathVariable Long id) {
		return this.dispensadoService.deletarDispensado(id);
	}
	
}
