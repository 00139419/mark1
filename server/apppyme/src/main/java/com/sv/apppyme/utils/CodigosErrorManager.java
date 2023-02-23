package com.sv.apppyme.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CodigosErrorManager {
	
	public static String getDescripcionError(int codigoError) {
		
		Logger log = Logger.getLogger(CodigosErrorManager.class);
		
		String descripcionError = "";
		
		Properties props = new Properties();
		
		try {
			String path = "/Users/dm420/Documents/git/mark1/server/apppyme/src/main/java/com/sv/apppyme/utils/properties/codigoError.properties";
			File file = new File(path);
			props.load(new FileInputStream(file));
			descripcionError = props.getProperty(String.valueOf(codigoError));
		} catch (Exception e) {
			log.info("Ocurrio un error obteniendo la descripcion de codigo de error " + codigoError);
			e.printStackTrace();
			log.info(e.getStackTrace().toString());
		}
		
		return descripcionError;
	}
	
}
