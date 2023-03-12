package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Desarrolladora;

public interface IRepoDesarrolladora {
	SuperGenericResponse insert(Desarrolladora desarrolladora);
	SuperGenericResponse update(Desarrolladora desarrolladora);
	SuperGenericResponse delete(Desarrolladora desarrolladora);
	GenericEntityResponse<Desarrolladora> getOneById(int id);
	GenericEntityResponse<List<Desarrolladora>> getAll();
}
