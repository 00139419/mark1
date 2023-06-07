package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producto {

	private int id;
	private Img img;
	private Categoria categoria;
	private Desarrolladora desarrolladora;
	private Plataforma plataforma;
	private double precio;
	private String nombre;
	private Date fechaDeLanzamiento;
	private int cantidadDisponible;
	private String descripcion;
	private Date fechaPublicacion;

	public Producto() {
		super();
	}

}
