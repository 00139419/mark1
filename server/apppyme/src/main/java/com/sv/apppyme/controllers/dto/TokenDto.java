package com.sv.apppyme.controllers.dto;

import com.sv.apppyme.dto.SuperGenericResponse;

import lombok.ToString;

@ToString
public class TokenDto extends SuperGenericResponse{
	
	private static final long serialVersionUID = 1L;
	private String token;
	private boolean esValido;
	private String username;

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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	

}
