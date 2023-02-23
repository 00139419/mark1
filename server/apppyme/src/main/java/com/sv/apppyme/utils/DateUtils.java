package com.sv.apppyme.utils;

import java.sql.Date;

public class DateUtils {
	
	public static Date convertirDateJavaToDateSQL(java.util.Date dateJava) {
		Date res = null;
		if(dateJava != null) {
			res = new Date(dateJava.getTime());
		}
		return res;
	}
	

}
