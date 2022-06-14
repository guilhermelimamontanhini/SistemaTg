package tg.Relatorios.TodosAlistados;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import tg.Alistados.Model.Alistado;
import tg.Alistados.Repository.AlistadoRepository;
import tg.Auxiliares.RelatorioEmPDF.RelatorioEmPDF;

@RestController
@RequestMapping("/todosAlistados/{tipo}")
@Service
public class RelatorioTodosAlistados {
	
	@Autowired
	private AlistadoRepository alistadosRepository;
	
	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosAlistados(@PathVariable Long tipo) {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/TodosAlistados.jrxml").getFile());
		
		List<Alistado> listaAlistados = new ArrayList<>();
		
		if (tipo == 0) {
			listaAlistados = this.alistadosRepository.findAllByRefratarioOrderByNomeAsc("S");
		} else {
			listaAlistados = this.alistadosRepository.findAllByRefratarioOrderByNomeAsc("N");
		}

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource alistados = new JRBeanCollectionDataSource(listaAlistados);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("alistados", alistados);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}

}
