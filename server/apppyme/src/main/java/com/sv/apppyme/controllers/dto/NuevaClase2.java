package com.sv.apppyme.controllers.dto;

public class NuevaClase2 {
	
	public static String key = "asdasdasd";
	
	public static String encriptar(String texto) {
		String textoEncriptado = texto + key;
		return textoEncriptado;
	}

}
