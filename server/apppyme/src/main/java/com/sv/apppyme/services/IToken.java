package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IToken {
	
	TokenDto creaToken(Usuario usuario) throws SrvValidacionException;

}
