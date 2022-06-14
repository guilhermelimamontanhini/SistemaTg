package tg.Relatorios.TodosDesligados;

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
import tg.Desligados.Model.Desligado;
import tg.Desligados.Repository.DesligadoRepository;

@RestController
@RequestMapping("/todosDesligados")
@Service
public class RelatorioTodosDesligados {
	
	@Autowired
	private DesligadoRepository desligadoRepository;
	
	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosDesligados() {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/TodosDesligados.jrxml").getFile());
		
		List<Desligado> listaDesligados = this.desligadoRepository.findAllByOrderByNomeAsc();

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource desligados = new JRBeanCollectionDataSource(listaDesligados);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("desligados", desligados);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}

}
