package com.sv.apppyme.controllers.dto;

import lombok.Data;

@Data
public class UsuarioDto {

	private String email;
	private String password;
	private String rol;

	public UsuarioDto() {
		super();
	}

	public UsuarioDto(String email, String password, String rol) {
		super();
		this.email = email;
		this.password = password;
		this.rol = rol;
	}
}
