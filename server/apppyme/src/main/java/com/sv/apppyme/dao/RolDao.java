package com.sv.apppyme.dao;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;

public interface RolDao {
	
	GenericEntityResponse<List<Rol>> getAll();

}
