package com.sv.apppyme.reports.repository;

import java.io.File;
import java.util.List;


public interface IReportManagerJasper {

	String generatedBase64JasperReport(List<Object> list, String typedocument);
	
	File generatedFileJasperReport(List<Object> list, String typedocument);
}
