package com.sv.apppyme.entities;

import java.util.Date;

public class Videojuego {
	
	private int id;
	private String nombre;
	private Categoria categoria;
	private double precio;
	private Date fechaDeLanzamiento;
	private Desarrolladora desarrolladora;
	private Img img;
	private int cantidadDisponible;
	private Plataforma plataforma;
	private String descripcion;
	
	public Videojuego() {
		super();
	}

	public Videojuego(int id, String nombre, Categoria categoria, double precio, Date fechaDeLanzamiento,
			Desarrolladora desarrolladora, Img img, int cantidadDisponible, Plataforma plataforma, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.fechaDeLanzamiento = fechaDeLanzamiento;
		this.desarrolladora = desarrolladora;
		this.img = img;
		this.cantidadDisponible = cantidadDisponible;
		this.plataforma = plataforma;
		this.descripcion = descripcion;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaDeLanzamiento() {
		return fechaDeLanzamiento;
	}

	public void setFechaDeLanzamiento(Date fechaDeLanzamiento) {
		this.fechaDeLanzamiento = fechaDeLanzamiento;
	}

	public Desarrolladora getDesarrolladora() {
		return desarrolladora;
	}

	public void setDesarrolladora(Desarrolladora desarrolladora) {
		this.desarrolladora = desarrolladora;
	}

	public Img getImg() {
		return img;
	}

	public void setImg(Img img) {
		this.img = img;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Videojuego [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", fechaDeLanzamiento=");
		builder.append(fechaDeLanzamiento);
		builder.append(", desarrolladora=");
		builder.append(desarrolladora);
		builder.append(", img=");
		builder.append(img);
		builder.append(", cantidadDisponible=");
		builder.append(cantidadDisponible);
		builder.append(", plataforma=");
		builder.append(plataforma);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append("]");
		return builder.toString();
	}

	
	
}
