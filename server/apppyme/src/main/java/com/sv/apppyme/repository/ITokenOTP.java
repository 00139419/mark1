package com.sv.apppyme.repository;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;

public interface ITokenOTP {
	
	SuperGenericResponse verificarTokenOTP(String numero);
	SuperGenericResponse insertToken(TokenOTP tokenOTP);

}
