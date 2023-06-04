package tg.Relatorios.TodosDespensados;

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
import tg.Despensados.Model.Despensado;
import tg.Despensados.Repository.DespensadoRepository;

@RestController
@RequestMapping("/todosDespensados")
@Service
public class RelatorioTodosDespensados {
	
	@Autowired
	private DespensadoRepository despensadoRepository;
	
	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosDespensados() {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/TodosDespensados.jrxml").getFile());
		
		List<Despensado> listaDespensados = this.despensadoRepository.findAllByOrderByNomeAsc();

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource despensados = new JRBeanCollectionDataSource(listaDespensados);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("despensados", despensados);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}

}
