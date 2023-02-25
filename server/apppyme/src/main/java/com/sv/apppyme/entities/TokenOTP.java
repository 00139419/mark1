package com.sv.apppyme.entities;

import java.util.Date;

import lombok.Data;

@Data
public class TokenOTP {
	int id;
	Usuario usuario;
	String token;
	Date fechaDeCreacion;
	Date fechaDeVencimiento;
	Boolean esValido;
	Boolean estaVerificado;

}
