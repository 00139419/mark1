package com.sv.apppyme.reports.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FormularioTokenOTPDto {
	
	private String tokenOTP;
	private Date expirationDate;
	
	

}
