package com.sv.apppyme.reports.repository.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.reports.repository.IReportManagerJasper;
import com.sv.apppyme.reports.utils.ReportConstantes;
import com.sv.apppyme.utils.Log4jUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class srvReportManagerJasperimpl implements IReportManagerJasper {

	Logger log = Logger.getLogger(srvReportManagerJasperimpl.class);

	@SuppressWarnings("unused")
	@Override
	public String generatedBase64JasperReport(List<Object> list, String typedocument) {
		log.info("::::[generatedBase64JasperReport]::::inicio servicio crear reporte::::");
		String base64 = "";
		JasperPrint report = null;
		HashMap<String, Object> params = null;
		JRBeanCollectionDataSource dataSource = null;
		switch (typedocument) {
		case "FV":
			try {
				dataSource = new JRBeanCollectionDataSource(list);
				report = JasperFillManager.fillReport(ReportConstantes.DIR_REP_VINCULACION, new HashMap<>(), dataSource);
				byte[] pdf = JasperExportManager.exportReportToPdf(report);
				base64 = Base64.encodeBase64String(pdf);
				log.info("::::[generatedBase64JasperReport]::::Reporte generado corretamente::::");
			} catch (JRException e) {
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Error de JRExeption generando el documento de vinculacion::::");
				log.info(Log4jUtils.getStackTrace(e));
			} catch (Exception e) {
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Error Generico generando el documento de vinculacion::::");
				log.info(Log4jUtils.getStackTrace(e));
			}
			break;
		case "factura":
			try {
				dataSource = new JRBeanCollectionDataSource(list);
				report = JasperFillManager.fillReport(ReportConstantes.DIR_REP_FACTURA, new HashMap<>(), dataSource);
				byte[] pdf = JasperExportManager.exportReportToPdf(report);
				base64 = Base64.encodeBase64String(pdf);
				log.info("::::[generatedBase64JasperReport]::::Reporte generado corretamente::::");
			} catch (JRException e) {
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Error de JRExeption generando el documento de vinculacion::::");
				log.info(Log4jUtils.getStackTrace(e));
			} catch (Exception e) {
				log.info("::::[ERROR]::::[generatedBase64JasperReport]::::Error Generico generando el documento de vinculacion::::");
				log.info(Log4jUtils.getStackTrace(e));
			}
			break;
		default: break;
		}
		return base64;
	}

	@SuppressWarnings("unused")
	@Override
	public File generatedFileJasperReport(List<Object> list, String typedocument) {
		log.info("::::[generatedBase64JasperReport]::::inicio servicio crear reporte::::");
		File file = null;
		JasperPrint report = null;
		HashMap<String, Object> params = null;
		JRBeanCollectionDataSource dataSource = null;
		String nombre = SecurityContextHolder.getContext().getAuthentication().getName() != null ? SecurityContextHolder.getContext().getAuthentication().getName() : "Ejemplo" ;
		switch (typedocument) {
		case "FV":
			try {
				dataSource = new JRBeanCollectionDataSource(list);
				report = JasperFillManager.fillReport(ReportConstantes.DIR_REP_VINCULACION, new HashMap<>(), dataSource);
				byte[] pdf = JasperExportManager.exportReportToPdf(report);
				file = new File("/Users/dm420/Documents/git/mark1/server/apppyme/src/main/java/com/sv/apppyme/reports/utils/saved/" + nombre +".pdf");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(pdf);
				fos.close();
				log.info("::::[generatedFileJasperReport]::::Reporte generado corretamente::::");
			} catch (JRException e) {
				log.info("::::[ERROR]::::[generatedFileJasperReport]::::Error de JRExeption generando el documento de vinculacion::::");
				log.info(Log4jUtils.getStackTrace(e));
			} catch (Exception e) {
				log.info("::::[ERROR]::::[generatedFileJasperReport]::::Error Generico generando el documento de vinculacion::::");
				log.info(Log4jUtils.getStackTrace(e));
			}
			break;
		default:
			break;
		}
		return file;
	}

}
