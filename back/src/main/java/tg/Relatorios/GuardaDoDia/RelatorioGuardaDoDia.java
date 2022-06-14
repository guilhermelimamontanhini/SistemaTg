package tg.Relatorios.GuardaDoDia;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import tg.Atirador.DTO.AtiradoresGuardaDTO;
import tg.Atirador.Model.Atirador;
import tg.Atirador.Repository.AtiradorRepository;
import tg.Auxiliares.RelatorioEmPDF.RelatorioEmPDF;
import tg.Guardas.DTO.GuardasDTO;
import tg.Guardas.FORM.DataGuarda;
import tg.Guardas.FORM.NovaGuarda;
import tg.Guardas.Model.Guardas;
import tg.Guardas.Repository.GuardasRepository;

@RestController
@RequestMapping("/todosIntegrantesGuarda")
@Service
public class RelatorioGuardaDoDia {
	
	@Autowired
	private GuardasRepository guardasRepository;
	
	@Autowired
	private AtiradorRepository atiradorRepository;
	
	@PostMapping
	public ResponseEntity<byte[]> gerarRelatorioTodosAlistados(@RequestBody @Valid DataGuarda data) {
		
		ClassLoader chamarArquivo = getClass().getClassLoader();
		File nomeArquivo = new File(chamarArquivo.getResource("Relatorios/IntegrantesGuarda.jrxml").getFile());
		
		List<Guardas> guardaDia = this.guardasRepository.findAllByDataGuarda(data.getDataGuarda());
		List<AtiradoresGuardaDTO> todosOsIntegrantes = new ArrayList<>();
		NovaGuarda dataTipoGuarda = new NovaGuarda();
		
		for(int cont = 0; cont <= guardaDia.size()-1; cont++) {
			dataTipoGuarda.setTipoGuarda(guardaDia.get(cont).getTipoGuarda());
			dataTipoGuarda.setDataGuarda(guardaDia.get(cont).getDataGuarda());
			Optional<Atirador> atirador = this.atiradorRepository.findByNomeGuerra(guardaDia.get(cont).getNomeGuerraIntegrante());
			AtiradoresGuardaDTO integranteGuarda = new AtiradoresGuardaDTO(atirador.get().getRa(), 
					atirador.get().getNomeGuerra(), atirador.get().getOcupacao());
			todosOsIntegrantes.add(integranteGuarda);
		}
		
		GuardasDTO guardaIntegrantes = new GuardasDTO(dataTipoGuarda,todosOsIntegrantes);
		List<AtiradoresGuardaDTO> atiradoresGuarda = new ArrayList<>();
		
		atiradoresGuarda = guardaIntegrantes.getListaIntegrantes();

		String BRLformato = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoDataAtual = DateTimeFormatter.ofPattern(BRLformato);
		
		JRBeanCollectionDataSource guarda = new JRBeanCollectionDataSource(atiradoresGuarda);
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		
		parametros.put("dataGuarda", data.getDataGuarda());
		parametros.put("atiradoresGuarda", guarda);
		parametros.put("dataAtual", LocalDateTime.now().format(formatoDataAtual));
		
		byte[] bytes = RelatorioEmPDF.geraRelatorioEmPDF(nomeArquivo.getAbsolutePath(), parametros);
		return RelatorioEmPDF.retornaResponseEntityRelatorio(bytes);
		
	}

}
