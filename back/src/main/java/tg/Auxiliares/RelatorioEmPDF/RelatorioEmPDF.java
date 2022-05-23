package tg.Auxiliares.RelatorioEmPDF;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@SuppressWarnings("deprecation")
public class RelatorioEmPDF {
	
	public static byte[]  geraRelatorioEmPDF(String jasperReportName, Map<String,Object> parametros) {
	    JRPdfExporter exporter = new JRPdfExporter();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    try {
	    	
	        InputStream jrxmlInput = new FileInputStream(new File(jasperReportName)); 
	        JasperDesign design = JRXmlLoader.load(jrxmlInput);
	        JasperReport jasperReport = JasperCompileManager.compileReport(design);

	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
	        exporter.exportReport();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Erro ao gerar Relatorio..."+e);
	    } finally {
	    }
	    return outputStream.toByteArray();
	}
	
	public static ResponseEntity<byte[]> retornaResponseEntityRelatorio(byte[] relatorio){
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}

}
