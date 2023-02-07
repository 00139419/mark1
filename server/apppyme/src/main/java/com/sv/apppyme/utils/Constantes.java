package com.sv.apppyme.utils;

public class Constantes {
	
	//constantes para respuesta de procesos
	public static final int SUCCES = 0;
	public static final int ERROR = -1;
	
	//constantes para mensajes
	public static final String OK = "OK";
	public static final String FAIL = "Fallo";
	
	//conestantes para la conexion con la base de datos
	public static final String DB_NAME = "/apppyme";
	public static final String DB_USERNAME = "postgres";
	public static final String DB_PASSWORD = "1234";
	public static final String DB_PUERTO = "5432";
	public static final String DB_POSTGRES_JDBC = "jdbc:postgresql://localhost:";
	
	
	//contantes para la tabla rol
	public static final String DB_TABLA_ROL = "rol";
	public static final String DB_TABLA_ROL_COL_ID = "id";
	public static final String DB_TABLA_ROL_COL_DESCRIPCION = "descripcion";
	public static final String DB_TABLA_ROL_QUERY_SELECT_EVERYTHING = "SELECT * FROM rol";
	
	//contestaes para los controladores
	public static final String ROOT_CTRL = "/apppyme/api/srv/";

}
