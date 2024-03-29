package com.sv.apppyme.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Img {

	private int id;
	private String base64;
	private Date fecha;

	public Img() {
		super();
	}

	public Img(int id) {
		super();
		this.id = id;
	}
}
