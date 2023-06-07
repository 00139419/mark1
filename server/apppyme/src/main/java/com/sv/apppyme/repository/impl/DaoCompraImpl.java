package com.sv.apppyme.repository.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCompraImpl {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_COMPRA = "compra";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_AGENTE_ID = "agente_id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_FECHA = "fecha";
	
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_COMPRA
			+ "(" + COL_AGENTE_ID + ","
			+ COL_USER_ID + ","
			+ COL_FECHA + ")"
			+ " VALUES (?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_COMPRA;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_COMPRA + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_COMPRA 
			+ " SET " 
				+ COL_AGENTE_ID + " = ?, "
				+ COL_USER_ID + " = ?, "
				+ COL_FECHA + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_COMPRA + " WHERE " + COL_ID + " = ?";

}
