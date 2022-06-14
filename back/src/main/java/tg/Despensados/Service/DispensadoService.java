package tg.Despensados.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import tg.Despensados.DTO.DispensadoDTO;
import tg.Despensados.Model.Despensado;
import tg.Despensados.Repository.DespensadoRepository;

@Service
public class DispensadoService {
	
	@Autowired
	private DespensadoRepository dispensadoRepository;
	
	/**
	 * 
	 * @description Método responsavél por listar todos os Dispensados
	 * @return ResponseEntity<List<DespensadoDTO>>
	 * */
	public ResponseEntity<List<DispensadoDTO>> listarDispensados() {
		
	    List<Despensado> dispensado = this.dispensadoRepository.findAll();
	    
		if(dispensado.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem dispensados cadastradas!");
		}
		return ResponseEntity.ok(DispensadoDTO.converterModelParaDTO(dispensado));
		
	}
	
	/**
	 * 
	 * @description Método responsavél por deletar permanentemente o dispensado da base de dados
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> deletarDispensado(@PathVariable Long id) {
		
		String mensagem = "";
		Optional<Despensado> dispensado = this.dispensadoRepository.findByIdDispensado(id);
		
		if(dispensado.isPresent()) {
			try {
				this.dispensadoRepository.delete(dispensado.get());
				mensagem = "Dispensado excluido com sucesso";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao deletar dispensado.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		} else {
			mensagem = "Dispensado não encontrado.";
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(mensagem);
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(mensagem);
		
	}

}
