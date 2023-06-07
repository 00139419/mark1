package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private int id;
	private String email;
	private String password;
	private Rol rol;
	private Boolean cuentaActiva;
	private Img img;
	private String nombre;
	private String departamento;
	private String municipio;
	private Date fechaNacimiento;
	private Date fechaEmiDoc;
	private Date fechaVenDoc;
	private String genero;
	private String numDoc;
	private String tipoDoc;

	public User() {
		super();
	}

	public User(String email) {
		super();
		this.email = email;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(int id) {
		super();
		this.id = id;
	}

}
