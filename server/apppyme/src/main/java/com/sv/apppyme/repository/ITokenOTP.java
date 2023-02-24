package com.sv.apppyme.repository;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;

import ch.qos.logback.core.subst.Token;

public interface ITokenOTP {
	
	GenericEntityResponse<TokenOTP> verificarTokenOTP(String numero);
	SuperGenericResponse insert(TokenOTP tokenOTP);
	SuperGenericResponse update(TokenOTP tokenOTP);

}
