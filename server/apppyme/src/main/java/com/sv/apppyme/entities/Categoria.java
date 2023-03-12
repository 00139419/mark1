package com.sv.apppyme.entities;

public class Categoria {
	
	private int id;
	private String nombre;
	
	public Categoria() {
		super();
	}

	public Categoria(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}
	
}
