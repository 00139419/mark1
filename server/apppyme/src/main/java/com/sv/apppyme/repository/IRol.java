package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;

public interface IRol {
	
	GenericEntityResponse<List<Rol>> getAll();
	GenericEntityResponse<Rol> getRolByDescripcition(String descripcion);
	GenericEntityResponse<Rol> getRolById(int id);

}
