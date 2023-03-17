package com.sv.apppyme.reports.repository.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sv.apppyme.reports.repository.IReportManagerJasper;
import com.sv.apppyme.reports.utils.ReportConstantes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class srvReportManagerJasperimpl implements IReportManagerJasper {

	Logger log = Logger.getLogger(srvReportManagerJasperimpl.class);

	@SuppressWarnings("unused")
	@Override
	public String generatedBase64JasperReport(List<Object> list, String typedocument) {
		log.info("::::[Incio]::::[generatedBase64JasperReport]::::Iniciando proceso de crear reporteria::::");
		log.info("::::[generatedBase64JasperReport]::::Informacion del documento a generar::::");
		log.info("::::[generatedBase64JasperReport]::::TipoDeDocumento::::" + typedocument + "::::");
		log.info("::::[generatedJageneratedBase64JasperReportsperReport]::::Informacion::::" + list.toString() + "::::");
		String base64 = "";
		JasperPrint report = null;
		HashMap<String, Object> params = null;
		JRBeanCollectionDataSource dataSource = null;
		switch (typedocument) {
		case "FV":
			try {
				log.info("::::" + new File(".").getAbsolutePath() + "::::RUTAAAA");
				log.info("::::[generatedBase64JasperReport]::::Plantilla de documento encontrado::::");
				dataSource = new JRBeanCollectionDataSource(list);
				log.info("::::[generatedBase64JasperReport]::::DataSource genrado correctamente::::");
				report = JasperFillManager.fillReport(ReportConstantes.DIR_REP_VINCULACION, new HashMap<>(), dataSource);
				log.info("::::[generatedBase64JasperReport]::::Reporte generado corretamente::::");
				byte[] pdf = JasperExportManager.exportReportToPdf(report);
				log.info("::::[generatedBase64JasperReport]::::reporte exportado a PDF correctamente::::");
				base64 = Base64.encodeBase64String(pdf);
				log.info("::::[generatedBase64JasperReport]::::PDF convertido a base64 correctamente::::");
			} catch (JRException e) {
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Error de JRExeption generando el documento de vinculacion::::");
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Mensaje::::" + e.getMessage() + "::::");
				e.printStackTrace();
			} catch (Exception e) {
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Error Generico generando el documento de vinculacion::::");
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Mensaje::::" + e.getMessage() + "::::");
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

		log.info("::::[FIN]::::[generatedJasperReport]::::Fin proceso de crear reporteria::::");
		return base64;
	}

	@SuppressWarnings("unused")
	@Override
	public File generatedFileJasperReport(List<Object> list, String typedocument) {
		log.info("::::[Incio]::::[generatedFileJasperReport]::::Iniciando proceso de crear reporteria::::");
		log.info("::::[generatedFileJasperReport]::::Informacion del documento a generar::::");
		log.info("::::[generatedFileJasperReport]::::TipoDeDocumento::::" + typedocument + "::::");
		log.info("::::[generatedFileJasperReport]::::Informacion::::" + list.toString() + "::::");
		File file = null;
		JasperPrint report = null;
		HashMap<String, Object> params = null;
		JRBeanCollectionDataSource dataSource = null;
		String nombre = SecurityContextHolder.getContext().getAuthentication().getName() != null ? SecurityContextHolder.getContext().getAuthentication().getName() : "Ejemplo" ;
		switch (typedocument) {
		case "FV":
			try {
				log.info("::::" + new File(".").getAbsolutePath() + "::::RUTAAAA");
				log.info("::::[generatedFileJasperReport]::::Plantilla de documento encontrado::::");
				dataSource = new JRBeanCollectionDataSource(list);
				log.info("::::[generatedFileJasperReport]::::DataSource genrado correctamente::::");
				report = JasperFillManager.fillReport(ReportConstantes.DIR_REP_VINCULACION, new HashMap<>(), dataSource);
				log.info("::::[generatedFileJasperReport]::::Reporte generado corretamente::::");
				byte[] pdf = JasperExportManager.exportReportToPdf(report);
				log.info("::::[generatedFileJasperReport]::::reporte exportado a PDF correctamente::::");
				file = new File("/Users/dm420/Documents/git/mark1/server/apppyme/src/main/java/com/sv/apppyme/reports/utils/saved/" + nombre +".pdf");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(pdf);
				log.info("::::[generatedFileJasperReport]::::File escrito con la informacion del Fv correctamente::::");
				fos.close();
			} catch (JRException e) {
				log.info("::::[ERROR]::::[generatedFileJasperReport]::::Error de JRExeption generando el documento de vinculacion::::");
				log.info("::::[ERROR]::::[generatedFileJasperReport]::::Mensaje::::" + e.getMessage() + "::::");
				e.printStackTrace();
			} catch (Exception e) {
				log.info("::::[ERROR]::::[generatedFileJasperReport]::::Error Generico generando el documento de vinculacion::::");
				log.info("::::[ERROR]::::[generatedFileJasperReport]::::Mensaje::::" + e.getMessage() + "::::");
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

		log.info("::::[FIN]::::[generatedFileJasperReport]::::Fin proceso de crear reporteria::::");
		return file;
	}

}
