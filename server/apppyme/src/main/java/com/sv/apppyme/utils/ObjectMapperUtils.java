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
		String json = "";
		try {
			json =  ToJson.writeValueAsString(objeto);
			log.info("::::[ObjectToJsonString]::::Datos convertidos correctamente a String Json::::!");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[ObjectToJsonString]::::Error en parsear datos a Json String::::Message::::" + e.getMessage() + "::::");
			log.info(Log4jUtils.getStackTrace(e));
		}
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <E> Object JsonToObject(String json, E entity) {
		
		ToJson = new ObjectMapper();
		Object res = null;
		
		try {
			res = ToJson.readValue(json, (Class<E>) entity);
			log.info("::::[ObjectToJsonString]::::Datos convertidos correctamente a object::::!");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[ObjectToJsonString]::::Error en parsear datos a object::::Message::::" + e.getMessage() + "::::");
			log.info(Log4jUtils.getStackTrace(e));
		}
		
		return res;
	}
}
