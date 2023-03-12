package com.sv.apppyme.entities;

import java.util.Date;

public class Reporte {
	
	private int id;
	private String base64;
	private Date fecha;
	private String hash;
	private Usuario usuario;
	
	public Reporte() {
		super();
	}

	public Reporte(int id, String base64, Date fecha, String hash, Usuario usuario) {
		super();
		this.id = id;
		this.base64 = base64;
		this.fecha = fecha;
		this.hash = hash;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reporte [id=");
		builder.append(id);
		builder.append(", base64=");
		builder.append(base64);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", hash=");
		builder.append(hash);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append("]");
		return builder.toString();
	}
	
}