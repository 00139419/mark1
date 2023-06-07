package com.sv.apppyme.repository.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DaoDetalleFacImpl {
	
	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_COMPRA = "detallefac";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_CABECERA_FACTURA_ID = "cabecerafac_id";
	public static final String COL_PRODUCTO_ID = "producto_id";
	public static final String COL_CANTIDAD = "cantidad";
	public static final String COL_PRECIO_UNITARIO = "preciounitario";
	public static final String COL_SUBTOTAL = "subtotal";
	
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_COMPRA
			+ "(" + COL_CABECERA_FACTURA_ID + ","
			+ COL_PRODUCTO_ID + ","
			+ COL_CANTIDAD + ","
			+ COL_PRECIO_UNITARIO + ","
			+ COL_SUBTOTAL + ")"
			+ " VALUES (?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_COMPRA;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_COMPRA + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_COMPRA 
			+ " SET " 
				+ COL_CABECERA_FACTURA_ID + " = ?, "
				+ COL_PRODUCTO_ID + " = ?, "
				+ COL_CANTIDAD + " = ?, "
				+ COL_PRECIO_UNITARIO + " = ?, "
				+ COL_SUBTOTAL + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_COMPRA + " WHERE " + COL_ID + " = ?";

}
