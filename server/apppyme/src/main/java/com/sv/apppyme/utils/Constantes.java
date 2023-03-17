package com.sv.apppyme.utils;

public class Constantes {
	
	//Spring security
	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	
	//constantes para respuesta de procesos
	public static final int SUCCES = 0;
	public static final int ERROR = -1;
	
	//constantes para mensajes
	public static final String OK = "OK";
	public static final String FAIL = "Fallo";
	
	//conestantes para JDBC
	public static final String DB_NAME = "/apppyme";
	public static final String DB_USERNAME = "postgres";
	public static final String DB_PASSWORD = "1234";
	public static final String DB_PUERTO = "5432";
	public static final String JDBC_URL = "jdbc:postgresql://localhost:";
	
	//roles de los usuarios
	public static final String ROL_ADMIN = "admin";
	public static final String ROL_USER = "user";


	//constantes para los controladores
	public static final String ROOT_CTRL = "/apppyme/api/srv";
	
	//constantes para JWT
	public static final String JWT_SECRETKEY = "ultraSecret123ultraSecret123ultraSecret123ultraSecret123";
	public static final int JWT_EXP_TIME_MILLIS = 1000 * 60 * 60; // 60 minutos
	public static final String JWT_SECRET_CLAIMS = "12345";
	
	//constantes para envio de mensajes
	public static final String EMAIL_HOST_ISSUER = "danielmorales.dm931@gmail.com";
	public static final String EMAIL_HOST_PASSWORD = "uknapwpvlygxwxch";

}
