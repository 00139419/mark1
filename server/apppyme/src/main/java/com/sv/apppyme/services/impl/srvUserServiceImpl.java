package com.sv.apppyme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sv.apppyme.services.IUserService;
import com.sv.apppyme.utils.TokenManager;

public class srvUserServiceImpl implements IUserService{

	@Autowired
	private TokenManager tokenManager;
	
	@Override
	public Boolean esTokenValido(String tokenJWT) throws Exception {
		
		if(!tokenManager.estaVigenteJWT(tokenJWT))
			return false;
		
		if(!tokenManager.contieneSecretClaimJWT(tokenJWT))
			return false;
		
		return true;
	}

}
