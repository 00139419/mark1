package com.sv.apppyme.email.dto;

import lombok.Data;

@Data
public class SendEmailDto {

	private String emailReceptor;
	private String subject;
	private String message;
	
}
