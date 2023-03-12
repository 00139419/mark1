package com.sv.apppyme.entities;


public class Img {
	
	private int id;
	private String base64;
	
	public Img() {
		super();
	}

	public Img(int id, String base64) {
		super();
		this.id = id;
		this.base64 = base64;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Imagen [id=");
		builder.append(id);
		builder.append(", base64=");
		builder.append(base64);
		builder.append("]");
		return builder.toString();
	}
	
		
}
