package com.sv.apppyme.reports.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sv.apppyme.reports.repository.IReportManagerJasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class srvReportManagerJasperimpl implements IReportManagerJasper {

	Logger log = Logger.getLogger(srvReportManagerJasperimpl.class);
	
	
	private String direccionReporteVinculacion = "/Users/dm420/Documents/git/mark1/server/apppyme/src/main/java/com/sv/apppyme/reports/utils/TestAppPyme.jasper";

	@Override
	public String generatedJasperReport(List<Object> list, String typedocument) {
		log.info("::::[Incio]::::[generatedJasperReport]::::Iniciando proceso de crear reporteria::::");
		log.info("::::[generatedJasperReport]::::Informacion del documento a generar::::");
		log.info("::::[generatedJasperReport]::::TipoDeDocumento::::" + typedocument + "::::");
		log.info("::::[generatedJasperReport]::::Informacion::::" + list.toString() + "::::");
		String base64 = "";
		JasperPrint report = null;
		HashMap<String, Object> params = null;
		JRBeanCollectionDataSource dataSource = null;
		switch (typedocument) {
		case "FV":
			try {
				log.info("::::" + new File(".").getAbsolutePath() + "::::RUTAAAA");
				log.info("::::[generatedJasperReport]::::Plantilla de documento encontrado::::");
				dataSource = new JRBeanCollectionDataSource(list);
				log.info("::::[generatedJasperReport]::::DataSource genrado correctamente::::");
				report = JasperFillManager.fillReport(direccionReporteVinculacion, new HashMap<>(), dataSource);
				log.info("::::[generatedJasperReport]::::Reporte generado corretamente::::");
				byte[] pdf = JasperExportManager.exportReportToPdf(report);
				log.info("::::[generatedJasperReport]::::reporte exportado a PDF correctamente::::");
				base64 = Base64.encodeBase64String(pdf);
				log.info("::::[generatedJasperReport]::::PDF convertido a base64 correctamente::::");
			} catch (JRException e) {
				log.info("::::[ERROR]::::[generatedJasperReport]::::Error de JRExeption generando el documento de vinculacion::::");
				log.info("::::[ERROR]::::[generatedJasperReport]::::Mensaje::::" + e.getMessage() + "::::");
				e.printStackTrace();
				base64 = "";
			} catch (Exception e) {
				log.info("::::[ERROR]::::[generatedJasperReport]::::Error Generico generando el documento de vinculacion::::");
				log.info("::::[ERROR]::::[generatedJasperReport]::::Mensaje::::" + e.getMessage() + "::::");
				e.printStackTrace();
				base64 = "";
			}
			break;
		default:
			break;
		}

		log.info("::::[FIN]::::[generatedJasperReport]::::Fin proceso de crear reporteria::::");
		return base64;
	}

}
