package tg.Relatorios.TodosDispensados;

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
import tg.Auxiliares.RelatorioEmPDF.RelatorioEmPDF;
import tg.Despensados.Model.Dispensado;
import tg.Despensados.Repository.DispensadoRepository;

@RestController
@RequestMapping("/todosDispensados")
@Service
public class RelatorioTodosDispensados {
	
	@Autowired
	private DispensadoRepository dispensadoRepository;
	
	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosDispensados() {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/TodosDispensados.jrxml").getFile());
		
		List<Dispensado> listaDispensados = this.dispensadoRepository.findAllByOrderByNomeAsc();

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource dispensados = new JRBeanCollectionDataSource(listaDispensados);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("dispensados", dispensados);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}

}
