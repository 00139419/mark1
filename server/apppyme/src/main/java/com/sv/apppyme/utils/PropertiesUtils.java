package com.sv.apppyme.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesUtils {
		
	@Value("${ambiente}")
	public String ambiente;

	public Properties obtenerProperties() {
		try {
			Properties mProperties = new Properties();
			
			if(ambiente.equals("dev"))
				mProperties.load(new FileReader("/Users/dm420/Documents/git/mark1/server/apppyme/src/main/resources/application-dev.properties"));
			
			if(ambiente.equals("prod"))
				mProperties.load(new FileReader("/Users/dm420/Documents/git/mark1/server/apppyme/src/main/resources/application-prod.properties"));
			
			return mProperties;
			
		} catch (Exception e) {
			return null;
		}
			
	}
	
	public static void main(String[] args) {
		System.out.println("Ruta: " + new File(".").getAbsolutePath());
	}


}
