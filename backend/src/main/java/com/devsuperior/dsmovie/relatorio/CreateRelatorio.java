package com.devsuperior.dsmovie.relatorio;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class CreateRelatorio {

	/// :: Gerar relatorio ja compilado
	public void gerarRelatorioCompilado(List<?> lista, HttpServletResponse response, Map<String, Object> parametros, String nomeRelatorio, String folder, Long empresaId, Boolean sendEmail) throws JRException, IOException {
		
		/// :: Get path Report Jasper relatorios.
		String pathRoot = "src/main/resources/jasper_relatorios/";
					
		/// :: Base.
		byte[] bytes = null;			
		
		/// :: Pegar o relatorio no diretorio informado.
		File report = ResourceUtils.getFile(pathRoot.concat(folder).concat(nomeRelatorio).concat(".jasper"));	

	    /// :: Passar as informações como paramentros e lista com dados para o relatorio
		JasperPrint jasperPrint = JasperFillManager.fillReport(report.getAbsolutePath(), parametros, new JRBeanCollectionDataSource(lista));
		
		/// :: Exportar o relatório para PDF.
		bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		
		/// :: Abrir pagina para exibir o relatorio
		response.setHeader("Content-disposition", "inline; fillename=" + nomeRelatorio.concat(".pdf"));
		
		response.getOutputStream().write(bytes);

	}
	
	/// :: Editar relatorio, mudar atributos, e aspectos e compilar o relatório
	public void gerarRelatorioEditado(List<?> lista, HttpServletResponse response, Map<String, Object> parametros, String nomeRelatorio, String folder, Boolean grupoPage, Boolean showLogo, Long empresaId, Boolean sendEmail) throws JRException, IOException {
		
		/// :: Get path Report Jasper relatorios.
		String pathRoot = "src/main/resources/jasper_relatorios/";
			
		/// :: Base.
		byte[] bytes = null;			
		
		/// :: Pegar o relatorio no diretorio informado.
		/// :: JasperReport report = JasperCompileManager.compileReport(pathRoot.concat(folder).concat(nomeRelatorio).concat(".jrxml"));	
		
		/// :: Pegar o relatorio no diretorio informado e editar aspectos do relatorio.
		JasperDesign report = JRXmlLoader.load(pathRoot.concat(folder).concat(nomeRelatorio).concat(".jrxml"));
		
		/// :: Aplicar alterações de grupo por pagina no relatorio
		if(grupoPage) {
			for (JRGroup grupo : report.getGroupsList()) {
				System.out.println(grupo.getName());
				
				if (grupo.getName().equals("GroupTipo")) {
					System.out.println("teste:" + grupo.isStartNewPage());
					grupo.setStartNewPage(true);
					System.out.println("teste:" + grupo.isStartNewPage());
				}
			}
		}
		
		/// :: Aplicar alterações na imagem do relatorio
		if(!showLogo) {
			
			report.getPageHeader().getElementByKey("tituloRelatorio").setX(0); 
			
			report.getPageHeader().getElementByKey("titleNomeEmpresa").setX(0);  
			report.getPageHeader().getElementByKey("separadorEmpresa").setX(80);
			report.getPageHeader().getElementByKey("nomeEmpresa").setX(100);
			
			report.getPageHeader().getElementByKey("titleDocumento").setX(0);
			report.getPageHeader().getElementByKey("separadorDocumento").setX(80);
			report.getPageHeader().getElementByKey("numeroDocumento").setX(100);
			
			report.getPageHeader().getElementByKey("titleInscricaoEstadual").setX(0);
			report.getPageHeader().getElementByKey("separadorInscricaoEstadual").setX(80);
			report.getPageHeader().getElementByKey("nomeInscricaoEstadual").setX(100);
		}
		
		/// :: Recompilar relatorio apos alterações dos elementos e atrinbutos
		JasperReport reportCompile = JasperCompileManager.compileReport(report);

	    /// :: Passar as informações como paramentros e lista com dados para o relatorio
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportCompile, parametros, new JRBeanCollectionDataSource(lista));
		
		/// :: Exportar o relatório para PDF.
		bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		
		/// :: Abrir pagina para exibir o relatorio
		response.setHeader("Content-disposition", "inline; fillename=" + nomeRelatorio.concat(".pdf"));
		
		response.getOutputStream().write(bytes);	
		
	}
	
}