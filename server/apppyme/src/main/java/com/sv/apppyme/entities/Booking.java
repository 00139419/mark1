package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Booking {

	private int id;
	private User user;
	private String listP;
	private Date fecha;
	private boolean vigente;
	private boolean pagada;
	private boolean cancelada;

	public Booking() {
		super();
	}

}
