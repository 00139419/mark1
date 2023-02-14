package com.sv.apppyme.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SrvUsernameNotFoundException extends UsernameNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public SrvUsernameNotFoundException(String msg) {
		super(msg);
	}

}
