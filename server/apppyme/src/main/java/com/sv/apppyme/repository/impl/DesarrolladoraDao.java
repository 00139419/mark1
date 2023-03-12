package com.sv.apppyme.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.repository.IRepoDesarrolladora;

public class DesarrolladoraDao implements IRepoDesarrolladora {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_DESARROLLADORA = "desarrolladora";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	
	// Consultas de la tabla
	public static final String INSERT = "INSERT INTO " + DB_TABLA_DESARROLLADORA + "(" + COL_NOMBRE + ")" + " VALUES (?)";
	public static final String SELECT = "SELECT * FROM " + DB_TABLA_DESARROLLADORA;
	public static final String SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_DESARROLLADORA
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String UPDATE = "UPDATE " + DB_TABLA_DESARROLLADORA 
			+ " SET " 
				+ COL_ID + " = ?, " 
				+ COL_NOMBRE + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String DELETE = "DELETE FROM " + DB_TABLA_DESARROLLADORA + " WHERE " + COL_ID + " = ?";
	
	
	@Override
	public SuperGenericResponse insert(Desarrolladora desarrolladora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse update(Desarrolladora desarrolladora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse delete(Desarrolladora desarrolladora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<Desarrolladora> getOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<List<Desarrolladora>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
