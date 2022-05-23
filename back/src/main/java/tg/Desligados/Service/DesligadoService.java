package tg.Desligados.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import tg.Desligados.DTO.DesligadoDTO;
import tg.Desligados.Model.Desligado;
import tg.Desligados.Repository.DesligadoRepository;

@Service
public class DesligadoService {
	
	@Autowired
	private DesligadoRepository desligadoRepository;
	
	/**
	 * 
	 * @description Método responsavél por listar todos os Desligados
	 * @return ResponseEntity<List<DesligadoDTO>>
	 * */
	public ResponseEntity<List<DesligadoDTO>> listarDesligados() {
		
	    List<Desligado> desligado = this.desligadoRepository.findAll();
	    
		if(desligado.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem desligados cadastrados!");
		}
		return ResponseEntity.ok(DesligadoDTO.converterModelParaDTO(desligado));
		
	}
	
	/**
	 * 
	 * @description Método responsavél por deletar permanentemente o desligado da base de dados
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> deletarDesligadoService(@PathVariable Long id) {
		
		String mensagem = "";
		Optional<Desligado> desligado = this.desligadoRepository.findByIdDesligado(id);
		
		if(desligado.isPresent()) {
			try {
				this.desligadoRepository.delete(desligado.get());
				mensagem = "Desligado excluido com sucesso";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao deletar desligado.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		} else {
			mensagem = "Despensado não encontrado.";
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(mensagem);
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(mensagem);
		
	}

}
