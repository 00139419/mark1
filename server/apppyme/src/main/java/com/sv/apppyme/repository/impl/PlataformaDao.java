package com.sv.apppyme.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.repository.IRepoPlataforma;

public class PlataformaDao implements IRepoPlataforma{
	
	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_PLATAFORMA = "plataforma";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	
	// Consultas de la tabla
	public static final String INSERT = "INSERT INTO " + DB_TABLA_PLATAFORMA + "(" + COL_NOMBRE + ")" + " VALUES (?)";
	public static final String SELECT = "SELECT * FROM " + DB_TABLA_PLATAFORMA;
	public static final String SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_PLATAFORMA 
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String UPDATE = "UPDATE " + DB_TABLA_PLATAFORMA 
			+ " SET " 
				+ COL_ID + " = ?, " 
				+ COL_NOMBRE + " = ?"
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String DELETE = "DELETE FROM " + DB_TABLA_PLATAFORMA + " WHERE " + COL_ID + " = ?";
	

	@Override
	public SuperGenericResponse insert(Plataforma plataforma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse update(Plataforma plataforma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse delete(Plataforma plataforma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<Plataforma> getOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<List<Plataforma>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
