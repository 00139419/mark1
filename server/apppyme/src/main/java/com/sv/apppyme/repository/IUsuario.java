package com.sv.apppyme.repository;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Usuario;

public interface IUsuario {

	SuperGenericResponse insertar(Usuario usuario); 
	GenericEntityResponse<Usuario> selectByUsername(String username);
}