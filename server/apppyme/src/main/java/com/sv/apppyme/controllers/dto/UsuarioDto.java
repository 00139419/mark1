package com.sv.apppyme.controllers.dto;

public class UsuarioDto {

	private String username;
	private String password;
	private String rol;

	public UsuarioDto() {
		super();
	}

	public UsuarioDto(String username, String password, String rol) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioDto [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", rol=");
		builder.append(rol);
		builder.append("]");
		return builder.toString();
	}
	
	

}
