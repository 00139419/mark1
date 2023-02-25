package com.sv.apppyme.entities;

public class Usuario {

	private int id;
	private String username;
	private String password;
	private Rol rol;
	private Boolean cuentaActiva;

	public Usuario() {
		super();
	}

	public Usuario(int id) {
		super();
		this.id = id;
	}
	
	public Usuario(String username) {
		super();
		this.username = username;
	}

	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Usuario(int id, String username, String password, Rol rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public Usuario(int id, String username, String password, Rol rol, Boolean cuentaActiva) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.cuentaActiva = cuentaActiva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Boolean getCuentaActiva() {
		return cuentaActiva;
	}

	public void setCuentaActiva(Boolean cuentaActiva) {
		this.cuentaActiva = cuentaActiva;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", rol=");
		builder.append(rol);
		builder.append("]");
		return builder.toString();
	}

}
