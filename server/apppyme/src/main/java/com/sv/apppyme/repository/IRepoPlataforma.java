package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Plataforma;

public interface IRepoPlataforma {
	SuperGenericResponse insert(Plataforma plataforma);
	SuperGenericResponse update(Plataforma plataforma);
	SuperGenericResponse delete(Plataforma plataforma);
	GenericEntityResponse<Plataforma> getOneById(int id);
	GenericEntityResponse<List<Plataforma>> getAll();
}
