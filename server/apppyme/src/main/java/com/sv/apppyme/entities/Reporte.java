package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reporte {

	private int id;
	private String base64;
	private Date fecha;
	private String nombre;
	private User userId;
	private String tipoDoc;
	private String numDoc;
	private float total;
	private float totalrec;
	private float totalcam;
	private String metpag;
	private String lv;

	public Reporte() {
		super();
	}

}