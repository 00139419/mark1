package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;

public interface IRepoCategoria {
	SuperGenericResponse insert(Categoria categoria);
	SuperGenericResponse update(Categoria categoria);
	SuperGenericResponse delete(Categoria categoria);
	GenericEntityResponse<Categoria> getOneById(int id);
	GenericEntityResponse<List<Categoria>> getAll();
}
