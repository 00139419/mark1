package com.sv.apppyme.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IAuth {
	
	
	/**
	 * 
	 * Metodo que se encarga de hacer el SignIn
	 * 
	 * @param userinfo
	 * @return un JWT para el usuario que ha iniciado sesion
	 * @throws SrvValidacionException
	 * @throws JsonProcessingException
	 * @author dm420
	 */
	GenericEntityResponse<TokenDto> login(UsuarioDto userinfo) throws SrvValidacionException, JsonProcessingException;

}
