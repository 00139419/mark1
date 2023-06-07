package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenOTP {
	int id;
	User usuario;
	String token;
	Date fechaDeCreacion;
	Date fechaDeVencimiento;
	Boolean esValido;
	Boolean estaVerificado;

	public TokenOTP() {
		super();
	}

}
