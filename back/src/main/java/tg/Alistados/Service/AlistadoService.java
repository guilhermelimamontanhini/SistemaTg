package tg.Alistados.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import tg.Alistados.DTO.AlistadoDTO;
import tg.Alistados.FORM.AlistadoFORM;
import tg.Alistados.Model.Alistado;
import tg.Alistados.Repository.AlistadoRepository;
import tg.Auxiliares.Motivo.MensagemMotivos;
import tg.Despensados.Model.Despensado;
import tg.Despensados.Repository.DespensadoRepository;

@Service
public class AlistadoService {
	
	@Autowired
	private AlistadoRepository alistadoRepository;
	
	@Autowired
	private DespensadoRepository dispensadoRepository;
	
	
	/**
	 * 
	 * @description Método responsável por listar todos os alistados
	 * @return ResponseEntity<List<AlistadoDTO>>
	 * */
	public ResponseEntity<List<AlistadoDTO>> listarAlistados() {
		
	    List<Alistado> alistados = this.alistadoRepository.findAllByOrderByNomeAsc();
	    
		if(alistados.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem alistados cadastrados!");
		}
		return ResponseEntity.ok(AlistadoDTO.converterModelParaDTO(alistados));
		
	}
	
	/**
	 * 
	 * @description Método para atualizar um alistado
	 * @param <Long> id
	 * @param <AlistadoFORM> alistadoForm
	 * @return ResponseEntity<AlistadoDTO>
 	 * */
	public ResponseEntity<AlistadoDTO> atualizarAlitado(@PathVariable Long id, @RequestBody @Valid AlistadoFORM alistadoForm) {
		Alistado alistado = alistadoForm.atualizarDadosDoAlistado(id, alistadoRepository);
		alistadoRepository.save(alistado);
		return ResponseEntity.ok(new AlistadoDTO(alistado));
	}
	
	
	/**
	 * 
	 * @description Método para cadastrar um alistado
	 * @param <AlistadoFORM> alistadoForm
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> cadastrarAlistado(@RequestBody @Valid AlistadoFORM alistadoForm) {
		
		String mensagem = "";
		boolean valido = false;
		
		Optional<Alistado> existeNomeAlistado = this.alistadoRepository.findByNome(alistadoForm.getNome());
		Optional<Alistado> existeCpfAlistado = this.alistadoRepository.findByCpf(alistadoForm.getCpf());
		
		if(existeNomeAlistado.isPresent() || existeCpfAlistado.isPresent()) {
			if (existeNomeAlistado.isPresent()) {
				mensagem = "Esse nome já está registrado";
			} else if (existeCpfAlistado.isPresent()) {
				mensagem = "Esse CPF já está registrado";
			}
		} else {
			try {
				Alistado alistado = new Alistado(alistadoForm);
				this.alistadoRepository.save(alistado);
				valido = true;
				mensagem = "Alistado cadastrado com sucesso";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao cadastrar alistado.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		}
		
		
		return ResponseEntity
				.status(valido ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
				.body(mensagem);
	}
	
	/**
	 * 
	 * @description Método responsavel por delatar o alistado da base de dados da Tabela Alistado e cadastrar esse dado na tabela de Desligados
	 * @param <Long> id
	 * @param <String> motivo
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> dispensarAlistado(@PathVariable Long id, @RequestBody @Valid MensagemMotivos mensagemMotivos) {
		String mensagem = "";
		Optional<Alistado> alistado = this.alistadoRepository.findByIdAlistado(id);
		
		if(alistado.isPresent()) {
			try {
				Despensado dispensado = new Despensado(alistado.get().getNome(), alistado.get().getCpf(), mensagemMotivos.getMotivo());
				this.dispensadoRepository.save(dispensado);
				this.alistadoRepository.delete(alistado.get());
				mensagem = "Alistado despensado com sucesso";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao despensar alistado.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Alistado não existe");
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(mensagem);
	}
}
