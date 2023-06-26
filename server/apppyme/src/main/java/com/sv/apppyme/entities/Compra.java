package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Compra {

	private int id;
	private User agente;
	private User user;
	private Date fecha;

	public Compra() {
		super();
	}

	public Compra(int id) {
		super();
		this.id = id;
	}

}
