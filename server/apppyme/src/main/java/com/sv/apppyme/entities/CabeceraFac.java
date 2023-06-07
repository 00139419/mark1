package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CabeceraFac {

	private int id;
	private User user;
	private Compra compra;
	private Img img;
	private String tipoDoc;
	private String numDoc;
	private Date fecha;
	private String MetPag;
	private String nombre;
	private double total;

	public CabeceraFac() {
		super();
	}

}
