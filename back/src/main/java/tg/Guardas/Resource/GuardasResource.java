package tg.Guardas.Resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tg.Guardas.DTO.GuardasDTO;
import tg.Guardas.FORM.DataGuarda;
import tg.Guardas.FORM.NovaGuarda;
import tg.Guardas.Service.GuardasService;

@RestController
@RequestMapping("/guarda")
public class GuardasResource {
	
	@Autowired
	private GuardasService guardasServices;
	
	/**
	 * 
	 * @description Método para adicionar integrantes na guarda
	 * @param <Long> ra
	 * @param <NovaGuarda> novaGuarda
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/cadastrar/{ra}")
	public ResponseEntity<String> adicionarIntegranteGuarda(@PathVariable Long ra, 
			@RequestBody @Valid NovaGuarda novaGuarda) {
		return this.guardasServices.adicionarIntegranteGuarda(ra, novaGuarda);
	}
	
	/**
	 * 
	 * @description Método para listar as guardas por dia 
	 * @param <DataGuarda> data
	 * @return ResponseEntity<GuardasDTO>
	 */
	@GetMapping
	public ResponseEntity<GuardasDTO> listarGuardaDoDia(@RequestBody @Valid DataGuarda data) {
		return this.guardasServices.listarGuardaDoDia(data);
	}

}
