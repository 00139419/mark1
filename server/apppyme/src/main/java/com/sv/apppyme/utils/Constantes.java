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
	public static final String JDBC_URL = "jdbc:postgresql://localhost:";
	
	//constantes para tipos de usuarios
	
	public static final String ROL_ADMIN = "admin";
	public static final String ROL_USER = "user";
	
	//contantes para la tabla roles
	public static final String DB_TABLA_ROL = "rol";
	public static final String DB_TABLA_ROL_COL_ID = "id";
	public static final String DB_TABLA_ROL_COL_DESCRIPCION = "descripcion";
	
	
	//constantes para la tabla usuarios
	public static final String DB_TABLA_USUARIO = "rol";
	public static final String DB_TABLA_USUARIO_COL_ID = "id";
	public static final String DB_TABLA_USUARIO_COL_USERNAME = "username";
	public static final String DB_TABLA_USUARIO_COL_PASSWORD = "password";
	public static final String DB_TABLA_USUARIO_COL_ROL_ID = "rol_id";
	public static final String DB_TABLA_USUARIO_QUERY_SELECT_EVERYTHING = "SELECT * FROM usuario";
	public static final String DB_TABLA_USUARIO_QUERY_INSERT = "INSERT INTO usuario (username, password, rol_id) VALUES (?,?,?)";
	
	//contestaes para los controladores
	public static final String ROOT_CTRL = "/apppyme/api/srv/";

}
