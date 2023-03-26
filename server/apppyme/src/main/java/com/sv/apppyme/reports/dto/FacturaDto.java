package com.sv.apppyme.reports.dto;

import lombok.Data;

@Data
public class FacturaDto {
	
	private Integer id;
	private String nombreCompleto;
	private String tipoDoc;
	private String numDoc;
	private String user;
	private String fecha;
	private String metPag;
	private String total;
	
}
