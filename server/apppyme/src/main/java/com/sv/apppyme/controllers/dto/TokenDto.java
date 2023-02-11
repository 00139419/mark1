package com.sv.apppyme.controllers.dto;


public class TokenDto{
	
	private String token;

	public TokenDto() {
		super();
	}

	public TokenDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenDto [token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
	
	

}
