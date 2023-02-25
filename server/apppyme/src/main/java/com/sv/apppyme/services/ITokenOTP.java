package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.ValidarTokenOTPDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ITokenOTP {
	
	TokenDto creaToken(Usuario usuario) throws SrvValidacionException;
	SuperGenericResponse validarToken(ValidarTokenOTPDto tokenOtp) throws SrvValidacionException;
	void eliminarTokensObsoletos();
	
}
