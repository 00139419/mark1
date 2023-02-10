package com.sv.apppyme.dao;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Usuario;

public interface IUsuarioDao {

	SuperGenericResponse insertar(Usuario usuario); 
	GenericEntityResponse<Usuario> selectByUsername(String username);
}
