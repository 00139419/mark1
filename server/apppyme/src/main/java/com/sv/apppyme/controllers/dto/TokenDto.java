package com.sv.apppyme.controllers.dto;

import com.sv.apppyme.dto.SuperGenericResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto extends SuperGenericResponse {

	private static final long serialVersionUID = 1L;
	private String token;
	private boolean esValido;
	private String email;

	public TokenDto() {
		super();
	}

	public TokenDto(int codigo, String mensaje) {
		super(codigo, mensaje);
	}

	public TokenDto(String token) {
		super();
		this.token = token;
	}

}
