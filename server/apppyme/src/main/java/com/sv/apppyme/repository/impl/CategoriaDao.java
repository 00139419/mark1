package com.sv.apppyme.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.repository.IRepoCategoria;

public class CategoriaDao implements IRepoCategoria {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_CATEGORIA = "categoria";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	
	// Consultas de la tabla
	public static final String INSERT = "INSERT INTO " + DB_TABLA_CATEGORIA + "(" + COL_NOMBRE + ")" + " VALUES (?)";
	public static final String SELECT = "SELECT * FROM " + DB_TABLA_CATEGORIA;
	public static final String SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_CATEGORIA + " WHERE " + COL_ID + " = ?";
	public static final String UPDATE = "UPDATE " + DB_TABLA_CATEGORIA 
			+ " SET " 
				+ COL_ID + " = ?, " 
				+ COL_NOMBRE + " = ? "
			+ "WHERE " 
				+ COL_ID + " = ?";
	public static final String DELETE = "DELETE FROM " + DB_TABLA_CATEGORIA + " WHERE " + COL_ID + " = ?";
	
	@Override
	public SuperGenericResponse insert(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse update(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse delete(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<Categoria> getOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<List<Categoria>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
