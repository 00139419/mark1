package com.sv.apppyme.repository.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DaoBookingImpl {
	
	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_BOOKING = "booking";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_LISTA_DE_PROD = "listp";
	public static final String COL_FECHA = "fecha";
	public static final String COL_VIGENTE = "vigente";
	public static final String COL_PAGADA = "pagada";
	public static final String COL_CANCELADA = "cancelada";
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_BOOKING
			+ "(" + COL_USER_ID + ","
			+ COL_LISTA_DE_PROD + ","
			+ COL_FECHA + ","
			+ COL_VIGENTE + ","
			+ COL_PAGADA + ","
			+ COL_CANCELADA + ")"
			+ " VALUES (?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_BOOKING;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_BOOKING + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_BOOKING 
			+ " SET " 
				+ COL_USER_ID + " = ?, "
				+ COL_LISTA_DE_PROD + " = ?, "
				+ COL_FECHA + " = ?, "
				+ COL_VIGENTE + " = ?, "
				+ COL_PAGADA + " = ?, "
				+ COL_CANCELADA + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_BOOKING + " WHERE " + COL_ID + " = ?";
	
	

}
