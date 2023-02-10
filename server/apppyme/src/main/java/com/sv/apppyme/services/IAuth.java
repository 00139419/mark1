package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IAuth {
	
	SuperGenericResponse login(UsuarioDto userinfo) throws SrvValidacionException;

}
