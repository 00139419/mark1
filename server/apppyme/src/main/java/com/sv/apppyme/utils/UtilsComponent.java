package com.sv.apppyme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UtilsComponent {
	
	@Autowired
	PropertiesUtils propertiesUtils;
	
	public String getAmbiente() {
		return propertiesUtils.obtenerProperties().getProperty("texto", "No se encontro");
	}
}
