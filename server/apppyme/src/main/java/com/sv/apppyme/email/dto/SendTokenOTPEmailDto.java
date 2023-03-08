package com.sv.apppyme.email.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class SendTokenOTPEmailDto extends SendEmailDto{
	private String token;
	private Date expirationDate;
	
	public SendTokenOTPEmailDto(String token, Date expirationDate) {
		super();
		this.token = token;
		this.expirationDate = expirationDate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
}
