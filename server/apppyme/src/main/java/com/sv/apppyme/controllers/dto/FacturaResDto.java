package com.sv.apppyme.controllers.dto;

import com.sv.apppyme.dto.SuperGenericResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaResDto extends SuperGenericResponse{
	private static final long serialVersionUID = 1L;
	private String pdf;
	
	public FacturaResDto() {
		super();
	}

	public FacturaResDto(String pdf) {
		super();
		this.pdf = pdf;
	}
	
}
