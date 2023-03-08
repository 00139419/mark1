package com.sv.apppyme.email.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SendEmailDto {

	private String emailReceptor;
	private String subject;
	private String message;
	private String emailType;
	private Date expirationDate;
	private String token;
	
}
