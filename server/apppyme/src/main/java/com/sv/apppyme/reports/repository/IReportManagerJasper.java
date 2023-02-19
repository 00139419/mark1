package com.sv.apppyme.reports.repository;

import java.util.List;

import com.sv.apppyme.reports.dto.FormularioVinculacionDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

public interface IReportManagerJasper {

	String generatedJasperReport(List<Object> list, String typedocument);
}
