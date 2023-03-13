package com.sv.apppyme.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.repository.IRepoVideojuego;

public class VideojuegoDao implements IRepoVideojuego {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_VIDEOJUEGO = "videojuego";

	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_CATEGORIA = "categoria_id";
	public static final String COL_PRECIO = "precio";
	public static final String COL_FECHA_LANZAMIENTO = "fechalanzamiento";
	public static final String COL_DESARROLLADORA = "desarrolladora_id";
	public static final String COL_IMG = "img_id";
	public static final String COL_CANTIDAD_DISPONIBLE = "cantidaddisponible";
	public static final String COL_PLATAFORMA = "plataforma";
	public static final String COL_DESCRIPCION = "descripcion";

	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_VIDEOJUEGO
			+ "(" 
				+ COL_NOMBRE + ", " 
				+ COL_CATEGORIA + ", " 
				+ COL_PRECIO + ", " 
				+ COL_FECHA_LANZAMIENTO + ", " 
				+ COL_DESARROLLADORA + ", " 
				+ COL_IMG + ", " 
				+ COL_CANTIDAD_DISPONIBLE + ", "
				+ COL_NOMBRE + ", " 
				+ COL_PLATAFORMA + ", " 
				+ COL_DESCRIPCION 
			+ ")"
		+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_VIDEOJUEGO;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_VIDEOJUEGO + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_VIDEOJUEGO 
			+ " SET " 
				+ COL_ID + " = ?, " 
				+ COL_NOMBRE + " = ? "
			+ "WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_VIDEOJUEGO + " WHERE " + COL_ID + " = ?";

	@Override
	public SuperGenericResponse insert(Videojuego videojuego) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse update(Videojuego videojuego) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse delete(Videojuego videojuego) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<Videojuego> getOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<List<Videojuego>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
