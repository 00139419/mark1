package com.sv.apppyme.repository;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Usuario;

public interface IRepoUsuario {

	SuperGenericResponse insert(Usuario usuario); 
	GenericEntityResponse<Usuario> getOneByUsername(String username);
	GenericEntityResponse<Usuario> getOneById(int id);
	SuperGenericResponse update(Usuario usuario);
}
