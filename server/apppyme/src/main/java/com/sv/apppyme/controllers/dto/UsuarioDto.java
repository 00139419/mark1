package com.sv.apppyme.controllers.dto;

import lombok.Data;

@Data
public class UsuarioDto {

	private String username;
	private String password;
	private String rol;
	private String email;

	public UsuarioDto() {
		super();
	}

	public UsuarioDto(String username, String password, String rol) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public UsuarioDto(String username, String password, String rol, String email) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}
}
