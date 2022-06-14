package tg.Relatorios.MembrosDoPelotao;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.Auxiliares.RelatorioEmPDF.RelatorioEmPDF;
import tg.Pelotao.Model.Pelotao;
import tg.Pelotao.Repository.PelotaoRepository;

@RestController
@RequestMapping("/membrosPelotao/{numero}")
@Service
public class RelatorioMembrosPelotao {
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	@Autowired
	private PelotaoRepository pelotaoRepository;
	
	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosAtiradores(@PathVariable Integer numero ) {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/MembrosPelotao.jrxml").getFile());
		
		Optional<Pelotao> pelotao = this.pelotaoRepository.findByNumero(numero);
		List<Atirador> listaAtiradores = this.atiradorRepository.findAllByNumeroPelotaoOrderByNomeAsc(numero);

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource atiradoresMembros = new JRBeanCollectionDataSource(listaAtiradores);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("nomePelotao", pelotao.get().getNome());
		parametros.put("atiradoresMembros", atiradoresMembros);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}
	
}
