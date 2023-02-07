package com.sv.apppyme.dto;

import java.io.Serializable;

public class SuperGenericResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String mensaje;

	public SuperGenericResponse() {
		super();
	}

	public SuperGenericResponse(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SuperGenericResponse {codigo=");
		builder.append(codigo);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append("}");
		return builder.toString();
	}
	
	

}
