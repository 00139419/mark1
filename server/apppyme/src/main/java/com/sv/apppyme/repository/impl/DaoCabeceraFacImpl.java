package com.sv.apppyme.repository.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCabeceraFacImpl {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_CABECERA_FACTURA = "cabecerafac";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_COMPRA_ID = "compra_id";
	public static final String COL_IMG_ID = "img_id";
	public static final String COL_TIPO_DOCUMENTO = "tipo	doc";
	public static final String COL_NUMERO_DOCUMENTO = "numdoc";
	public static final String COL_FECHA = "fecha";
	public static final String COL_METODO_PAGO = "met_pag";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_TOTAL = "total";
	
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_CABECERA_FACTURA
			+ "(" + COL_USER_ID + ","
			+ COL_COMPRA_ID + ","
			+ COL_IMG_ID + ","
			+ COL_TIPO_DOCUMENTO + ","
			+ COL_NUMERO_DOCUMENTO + ","
			+ COL_FECHA + ","
			+ COL_METODO_PAGO + ","
			+ COL_NOMBRE + ","
			+ COL_TOTAL + ")"
			+ " VALUES (?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_CABECERA_FACTURA;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_CABECERA_FACTURA + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_CABECERA_FACTURA 
			+ " SET " 
				+ COL_USER_ID + " = ?, "
				+ COL_COMPRA_ID + " = ?, "
				+ COL_IMG_ID + " = ?, "
				+ COL_TIPO_DOCUMENTO + " = ?, "
				+ COL_NUMERO_DOCUMENTO + " = ?, "
				+ COL_FECHA + " = ?, "
				+ COL_METODO_PAGO + " = ?, "
				+ COL_NOMBRE + " = ?, "
				+ COL_TOTAL + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_CABECERA_FACTURA + " WHERE " + COL_ID + " = ?";

}
