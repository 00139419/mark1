package com.sv.apppyme.exception;

public class SrvValidacionException extends Exception {

	private static final long serialVersionUID = 1L;

	private int codigo;
	private String mensaje;

	public SrvValidacionException(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	
}
