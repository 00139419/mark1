package com.sv.apppyme.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Desarrolladora {

	private int id;
	private String nombre;
	private Img img;

	public Desarrolladora() {
		super();
	}

	public Desarrolladora(int id) {
		super();
		this.id = id;
	}
	
}
