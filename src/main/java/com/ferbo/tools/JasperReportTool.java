package com.ferbo.tools;

import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperReportTool<T> {
	
	private static Logger log = LogManager.getLogger(JasperReportTool.class);
	
	public void exportToPDF(List<T> dataList, Map<String, Object> jrParameters, String pathToJRXML, OutputStream output)
	throws InventarioException {
		JasperDesign design = null;
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		JRBeanCollectionDataSource dataSource = null;
		
		try {
			jrParameters.put("REPORT_LOCALE", new Locale("es", "MX"));
			
			dataSource = new JRBeanCollectionDataSource(dataList);
			design = JRXmlLoader.load(pathToJRXML);
			report = JasperCompileManager.compileReport(design);
			jasperPrint = JasperFillManager.fillReport(report, jrParameters, dataSource);
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);
		} catch (JRException ex) {
			log.error("Problema al exportar a Jasper (PDF)...", ex);
			throw new InventarioException("Problema para exportar a jasper", ex);
		} finally {
			IOTool.close(output);
		}
	}
}
