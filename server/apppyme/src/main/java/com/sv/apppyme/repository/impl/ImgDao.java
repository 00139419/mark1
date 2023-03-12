package com.sv.apppyme.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.repository.IRepoImg;

public class ImgDao implements IRepoImg{
	
	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_IMG = "img";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_BASE64 = "base64";
	
	// Consultas de la tabla
	public static final String INSERT = "INSERT INTO " + DB_TABLA_IMG + "(" + COL_BASE64 + ")" + " VALUES (?)";
	public static final String SELECT = "SELECT * FROM " + DB_TABLA_IMG;
	public static final String SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_IMG 
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String UPDATE = "UPDATE " + DB_TABLA_IMG 
			+ " SET " 
				+ COL_ID + " = ?, " 
				+ COL_BASE64 + " = ?"
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String DELETE = "DELETE FROM " + DB_TABLA_IMG + " WHERE " + COL_ID + " = ?";
	

	@Override
	public SuperGenericResponse insert(Img img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse update(Img img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse delete(Img img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<Img> getOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<List<Img>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
