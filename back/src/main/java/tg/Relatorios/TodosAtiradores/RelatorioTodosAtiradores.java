package tg.Relatorios.TodosAtiradores;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.Auxiliares.RelatorioEmPDF.RelatorioEmPDF;

@RestController
@RequestMapping("/todosAtiradores")
@Service
public class RelatorioTodosAtiradores {
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosAtiradores() {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/TodosAtiradores.jrxml").getFile());
		
		List<Atirador> listaAtiradores = this.atiradorRepository.findAllByOrderByNomeAsc();

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource atiradores = new JRBeanCollectionDataSource(listaAtiradores);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("atiradores", atiradores);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}

}
