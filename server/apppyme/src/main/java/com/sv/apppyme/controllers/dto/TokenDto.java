package com.sv.apppyme.controllers.dto;

import com.sv.apppyme.dto.SuperGenericResponse;


public class TokenDto extends SuperGenericResponse{
	
	private static final long serialVersionUID = 1L;
	private String token;
	private boolean esValido;

	public TokenDto() {
		super();
	}

	public TokenDto(String token) {
		super();
		this.token = token;
	}
	
	public TokenDto(int codigo, String mensaje) {
		super();
		this.setCodigo(codigo);
		this.setMensaje(mensaje);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isEsValido() {
		return esValido;
	}

	public void setEsValido(boolean esValido) {
		this.esValido = esValido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenDto [token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
	
	

}
