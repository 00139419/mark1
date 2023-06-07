package com.sv.apppyme.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plataforma {

	private int id;
	private String nombre;

	public Plataforma() {
		super();
	}

	public Plataforma(int id) {
		super();
		this.id = id;
	}

}