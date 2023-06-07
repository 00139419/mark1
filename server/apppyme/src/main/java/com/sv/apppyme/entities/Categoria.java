package com.sv.apppyme.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Categoria {

	private int id;
	private String nombre;

	public Categoria() {
		super();
	}

	public Categoria(int id) {
		super();
		this.id = id;
	}

}
