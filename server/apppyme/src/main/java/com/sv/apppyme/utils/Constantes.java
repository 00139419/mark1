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
	
	//contantes para la tablas
	public static final String DB_TABLA_ROL = "rol";
	public static final String DB_TABLA_USUARIO = "usuario";

	//constantes para los controladores
	public static final String ROOT_CTRL = "/apppyme/api/srv/";
	
	//constantes para JWT
	public static final String JWT_SECRETKEY = "ultraSecret123ultraSecret123ultraSecret123ultraSecret123";
	public static final int JWT_EXP_TIME_MILLIS = 1000 * 60 * 60; // 60 minutos

}
