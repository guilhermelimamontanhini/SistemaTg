package tg.FATT.Service;

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

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.FATT.DTO.FATTDTO;
import tg.FATT.FORM.AdicionarJustificativa;
import tg.FATT.Model.FATT;
import tg.FATT.Repository.FATTRepository;

@Service
public class FATTService {
	
	@Autowired
	private FATTRepository fattRepository;
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	/**
	 * 
	 * @description Método responsável por listar todos os fatts
	 * @return ResponseEntity<List<AtiradorDTO>>
	 * */
	public ResponseEntity<List<FATTDTO>> listarTodosOsFATTs() {
		
		List<FATT> listaFatt = this.fattRepository.findAll();
		
		if(listaFatt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem fatts cadastrados!");
		}
		
		
		return ResponseEntity.ok(FATTDTO.converterModelParaDTO(listaFatt));
		
	}
	
	/**
	 * 
	 * @description Método para adiocionar ou atualizar justificativa do FATT
	 * @param <Long> idFatt
	 * @param <AdicionarJustificativa> adicionarJustificativa
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> adicionarJustificativa(@PathVariable Long id, 
			@RequestBody @Valid AdicionarJustificativa adicionarJustificativa) {
		
		String mensagem = "";
		boolean valido = false;
		
		FATT fatt = adicionarJustificativa.atualizarJustificativa(id, fattRepository);
		
		try {
			fattRepository.save(fatt);
			valido = true;
			mensagem = "Justificativa atualizada.";
		} catch(Exception e) {
			e.printStackTrace();
			mensagem = "Erro ao atualizar justificativa.";
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
	 * @description Método que aplica o FATT no atirador, se tiver uma justificativa valida ele só receberá os pontos base
	 * @param <Long> id
	 * @param <Boolean> justificado
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> aplicarFatt(@PathVariable Long id, @PathVariable Boolean justificado) {
		
		String mensagem = "";
		Optional<FATT> fatt = fattRepository.findByIdFatt(id);
		Optional<Atirador> atiradorComFatt = this.atiradorRepository.findByRa(fatt.get().getRaAtirador());
		
		if(fatt.isPresent()) {
			if(fatt.get().getJustificativa().isEmpty() || !justificado) {
				atiradorComFatt.get().setPontos(atiradorComFatt.get().getPontos() + 4);
			} else if (justificado) {
				atiradorComFatt.get().setPontos(atiradorComFatt.get().getPontos() + 2);
			}
			try {
				this.atiradorRepository.save(atiradorComFatt.get());
				this.fattRepository.delete(fatt.get());
				mensagem = "FATT aplicado com sucesso";
			} catch(Exception e) {
				e.printStackTrace();
				mensagem = "Erro ao aplicar fatt.";
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagem);
			}
		} else {
			mensagem = "FATTs não encontrado.";
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(mensagem);
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(mensagem);
		
	}

}
