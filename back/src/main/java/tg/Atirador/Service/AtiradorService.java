package tg.Atirador.Service;

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

import tg.Alistados.Model.Alistado;
import tg.Alistados.Repository.AlistadoRepository;
import tg.Atirador.DTO.AtiradorDTO;
import tg.Atirador.FORM.AlterarOcupacao;
import tg.Atirador.FORM.AtualizarAtirador;
import tg.Atirador.FORM.NovoAtirador;
import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.Auxiliares.Motivo.MensagemMotivos;
import tg.Auxiliares.OcorrenciaJustificativa.OcorrenciaJustificativa;
import tg.Desligados.Model.Desligado;
import tg.Desligados.Repository.DesligadoRepository;
import tg.FATT.Model.FATT;
import tg.FATT.Repository.FATTRepository;

@Service
public class AtiradorService {
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	@Autowired
	private AlistadoRepository alistadoRepository;
	
	@Autowired
	private DesligadoRepository desligadosRepository;
	
	@Autowired
	private FATTRepository fattRepository;
	
	/**
	 * 
	 * @description Método responsável por cadastrar um novo atirador
	 * @param <Long> id
	 * @param <NovoAtirador> atirador
	 * */
	public ResponseEntity<String> cadastrarAtiradorService(@PathVariable Long id, @RequestBody @Valid NovoAtirador dadosNovoAtirador) {
		
		String mensagem = "";
		boolean valido = false;
		
		List<Atirador> quantidadeAtiradores = this.atiradorRepository.findAll();
		
		if (quantidadeAtiradores.size() <= 99) {
			Optional<Alistado> alistado = this.alistadoRepository.findByIdAlistado(id);
			
			if(alistado.isPresent()) {
				try {
					Atirador novoAtirador = new Atirador(alistado.get().getNome(), alistado.get().getCpf(), dadosNovoAtirador);
					this.atiradorRepository.save(novoAtirador);
					this.alistadoRepository.delete(alistado.get());
					mensagem = "Atirador cadastrado com sucesso";
					valido = true;
				} catch(Exception e) {
					e.printStackTrace();
					mensagem = "Erro ao cadastrar atirador.";
					return ResponseEntity
							.status(HttpStatus.BAD_REQUEST)
							.body(mensagem);
				}
			} else {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("Alistado não existe");
			}
		} else {
			return ResponseEntity
					.status(HttpStatus.LENGTH_REQUIRED)
					.body("A existem mais de cem atiradores cadastrados");
		}
		
		return ResponseEntity
				.status(valido ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
				.body(mensagem);
		
	}
	
	/**
	 * 
	 * @description Método responsável por listar todos os atiradores
	 * @return ResponseEntity<List<AtiradorDTO>>
	 * */
	public ResponseEntity<List<AtiradorDTO>> listarTodosOsAtiradores() {
		
		List<Atirador> listaAtiradores = this.atiradorRepository.findAllByOrderByNomeAsc();
		
		if(listaAtiradores.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem atiradores cadastrados!");
		}
		
		return ResponseEntity.ok(AtiradorDTO.converterModelParaDTO(listaAtiradores));
		
	}
	
	/**
	 * 
	 * @description Método responsável por atualizar o Atirador
	 * @param <Long> id
	 * @param <AtualizarAtirador> atualizarAtirador
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> atualizarDadosAtiradorService(@PathVariable Long ra, @RequestBody @Valid AtualizarAtirador atualizarAtirador) {
		
		String mensagem = "";
		boolean valido = false;
		
		Atirador atirador = atualizarAtirador.atualizarDadosDoAtirador(ra, atiradorRepository);
		
			try {
				atiradorRepository.save(atirador);
				valido = true;
				mensagem = "Atirador atualizado.";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao atualizar atirador.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		
		return ResponseEntity
				.status(valido ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
				.body(mensagem);
		
	}
	
	/**
	 * 
	 * @description Método responsável para alterar a ocupaçao de um atirador Atirador
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> promoverAtiradorParaAlunoService(@PathVariable Long ra, @PathVariable Boolean promover) {
		
		String mensagem = "";
		boolean valido = false;
		
		AlterarOcupacao alterarOcupacao = new AlterarOcupacao();
		Atirador atirador = alterarOcupacao.atualizarDadosDoAtirador(ra, promover, atiradorRepository);
		
		try {
			atiradorRepository.save(atirador);
			valido = true;
			mensagem = "Ocupaçao atualizada.";
		} catch(Exception e) {
			e.printStackTrace();
			mensagem = "Erro ao atualizar atirador.";
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(mensagem);
		}
		
		return ResponseEntity
				.status(valido ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
				.body(mensagem);
		
	}
	
   /**
	 * 
	 * @description Método responsável para adicionar um FATT no Atirador
	 * @param <Long> id
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> adicionarFATD(@PathVariable Long ra, @RequestBody @Valid OcorrenciaJustificativa ocorrenciaJustificativa) {
		
		String mensagem = "";
		boolean valido = false;
		
		Optional<Atirador> atirador = atiradorRepository.findByRa(ra);
		
		
		if(atirador.isPresent()) {
			FATT novoFatt = new FATT(atirador.get().getRa(), atirador.get().getNomeGuerra(), ocorrenciaJustificativa);
			try {
				fattRepository.save(novoFatt);
				valido = true;
				mensagem = "Apuração de FATT concluida.";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao apurar FATT.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		} else {
			mensagem = "Atirador não existe.";
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(mensagem);
		}
		
		return ResponseEntity
				.status(valido ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
				.body(mensagem);
		
	}
	
	/**
	 * 
	 * @description Método para desligar um atirador e adiciona-lo a tabela de Desligados
	 * @param <Long> id
	 * @param <MensagemMotivos> motivoFORM
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> desligarAtiradorService(@PathVariable Long ra, @RequestBody @Valid MensagemMotivos mensagemMotivos) {
		String mensagem = "";
		
		Optional<Atirador> atiradorDesligado = this.atiradorRepository.findByRa(ra);
		
		if(atiradorDesligado.isPresent()) {
			try {
				Desligado desligado = new Desligado(atiradorDesligado.get().getNome(), 
													atiradorDesligado.get().getCpf(), mensagemMotivos.getMotivo());
				this.desligadosRepository.save(desligado);
				this.atiradorRepository.delete(atiradorDesligado.get());
				mensagem = "Atirador desligado com sucesso";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao desligar atirador.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Atirador não existe");
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(mensagem);
	}

}
