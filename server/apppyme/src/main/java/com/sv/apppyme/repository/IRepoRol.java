package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;

public interface IRepoRol {
	
	GenericEntityResponse<List<Rol>> getAll();
	GenericEntityResponse<Rol> getOneByDescripcition(String descripcion);
	GenericEntityResponse<Rol> getOneById(int id);

}
