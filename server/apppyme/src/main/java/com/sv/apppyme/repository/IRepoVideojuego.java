package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;

public interface IRepoVideojuego {
	SuperGenericResponse insert(Videojuego videojuego);
	SuperGenericResponse update(Videojuego videojuego);
	SuperGenericResponse delete(Videojuego videojuego);
	GenericEntityResponse<Videojuego> getOneById(int id);
	GenericEntityResponse<List<Videojuego>> getAll();
}
