package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IData {
	//metodos para roles
	GenericEntityResponse<List<Rol>> getAllRoles() throws SrvValidacionException;
	
	
	//motodos para usuarios
	SuperGenericResponse insertarUsuario(UsuarioDto userInfo) throws SrvValidacionException;
	GenericEntityResponse<Usuario> obtenerUsuarioByUsername(UsuarioDto userInfo) throws SrvValidacionException;
}
