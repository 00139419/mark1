package com.sv.apppyme.reports.exception;

public class JasperReportExeption  extends Exception{

	private static final long serialVersionUID = 1L;
	
	String message;

	public JasperReportExeption(String message) {
		super();
		this.message = message;
	}
	
}
