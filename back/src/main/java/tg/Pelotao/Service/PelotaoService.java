package tg.Pelotao.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.Pelotao.DTO.PelotaoDTO;
import tg.Pelotao.FORM.NovoPelotao;
import tg.Pelotao.Model.Pelotao;
import tg.Pelotao.Repository.PelotaoRepository;

@Service
public class PelotaoService {
	
	@Autowired
	private PelotaoRepository pelotaoRepository;
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	/**
	 * 
	 * @description Método responsável por cadastrar os pelotões, quando houver 4 pelotões, os atiradores serão cadastrados nos 
	 * mesmos
	 * @param <NovoPelotao> novoPelotao
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> cadastrarPelotao(@RequestBody @Valid NovoPelotao novoPelotao) {
		
		String mensagem = "";
		boolean valido = false;
		
		Optional<Pelotao> numeroExistente = this.pelotaoRepository.findByNumero(novoPelotao.getNumero());
		Optional<Pelotao> nomeExistente = this.pelotaoRepository.findByNome(novoPelotao.getNome());
		
		if(numeroExistente.isPresent() || nomeExistente.isPresent()) {
			if (nomeExistente.isPresent()) {
				mensagem = "Esse nome já está registrado";
			} else if (numeroExistente.isPresent()) {
				mensagem = "Esse número já está registrado";
			}
		} else {
			List<Pelotao> listaDePelotoes = this.pelotaoRepository.findAll();
			try {
				if(listaDePelotoes.size() <= 2) {
					Pelotao novoPelotaoCadastraddo = new Pelotao(novoPelotao);
					this.pelotaoRepository.save(novoPelotaoCadastraddo);
					valido = true;
					mensagem = "Pelotão cadastrado com sucesso";
				}
				else if (listaDePelotoes.size() == 4) {
					valido = false;
					mensagem = "Já existe o maximo de pelotões cadastrados";
				} 
			  else {
					Pelotao novoPelotaoCadastraddo = new Pelotao(novoPelotao);
					this.pelotaoRepository.save(novoPelotaoCadastraddo);
					this.cadastrarAtiradoresNosPelotoes();
					valido = true;
					mensagem = "Atiradores cadastrados nos pelotões concluido com sucesso";
				}
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
	 * @description Método responsável por cadastrar todos os atiradores em todos os pelotões
	 * */
	private void cadastrarAtiradoresNosPelotoes() {
		List<Pelotao> listaDePelotoes = this.pelotaoRepository.findAll();
		List<Atirador> todosAtiradorOrdemAlfabetica = this.atiradorRepository.findAllByOrderByNomeAsc();
		
		for(int cont = 0; cont <= 99; cont++) {
			if(cont < 25) {
				todosAtiradorOrdemAlfabetica.get(cont).setNumeroPelotao(listaDePelotoes.get(0).getNumero());
			}
		   if(cont >= 25 && cont < 49) {
				todosAtiradorOrdemAlfabetica.get(cont).setNumeroPelotao(listaDePelotoes.get(1).getNumero());
			}
			if(cont >= 50 && cont < 74) {
				todosAtiradorOrdemAlfabetica.get(cont).setNumeroPelotao(listaDePelotoes.get(2).getNumero());
			}
			if(cont >= 75) {
				todosAtiradorOrdemAlfabetica.get(cont).setNumeroPelotao(listaDePelotoes.get(3).getNumero());
			}
		}
		this.atiradorRepository.saveAll(todosAtiradorOrdemAlfabetica);
		
	}

	/**
	 * 
	 * @description Método responsável por listar todos os pelotões e seus integrantes
	 * @return ResponseEntity<List<PelotaoDTO>>
	 * */
	public ResponseEntity<List<PelotaoDTO>> listarTodosOsPelotoes() {
		
		List<PelotaoDTO> listaPelotoesIntegrantes = new ArrayList<>();
		
		for(int cont = 1; cont <= 4; cont++) {
			Optional<Pelotao> pelotao = this.pelotaoRepository.findByNumero(cont);
			List<Atirador> atiradores = this.atiradorRepository.findAllByNumeroPelotaoOrderByNomeAsc(cont);
			PelotaoDTO registro = new PelotaoDTO(pelotao,atiradores);
			listaPelotoesIntegrantes.add(registro);
		}
		
		return ResponseEntity.ok(listaPelotoesIntegrantes); 
	
		
	}
	
}
