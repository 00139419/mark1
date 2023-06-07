package com.sv.apppyme.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rol {

	private int id;
	private String descripcion;

	public Rol() {
		super();
	}

	public Rol(int id) {
		super();
		this.id = id;
	}

}
