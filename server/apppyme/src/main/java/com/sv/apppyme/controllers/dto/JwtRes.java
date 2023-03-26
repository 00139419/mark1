package com.sv.apppyme.controllers.dto;

import com.sv.apppyme.dto.SuperGenericResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRes extends SuperGenericResponse{
	private static final long serialVersionUID = 1L;
	private String jwt;
	
	public JwtRes() {
		super();
	}

	public JwtRes(String jwt) {
		super();
		this.jwt = jwt;
	}
	
}
