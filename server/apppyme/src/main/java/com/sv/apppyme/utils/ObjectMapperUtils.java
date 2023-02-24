package com.sv.apppyme.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

	static Logger log  =  Logger.getLogger(ObjectMapperUtils.class);
	
	@Autowired
	static ObjectMapper ToJson;
	
	public static <E> String ObjectToJsonString(E entity) {
		
		ToJson = new ObjectMapper();
		Object objeto = entity;
		log.info("::::[INICIO]::::[ObjectToJsonString]::::Inicio metodo leer valor como String::::");
		log.info("::::[INICIO]::::[ObjectToJsonString]::::Datos recibidos::::value::::"  + objeto.toString() + "::::");
		String json = "";
		try {
			json =  ToJson.writeValueAsString(objeto);
			log.info("::::[ObjectToJsonString]::::Datos convertidos correctamente::::!");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[ObjectToJsonString]::::Error en parsear datos a Json String::::");
			log.info("::::[ERROR]::::[ObjectToJsonString]::::Mensaje::::value:::::" + e.getMessage() + "::::");
			e.printStackTrace();
		}
		return json;
	}
}
