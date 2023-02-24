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
	
	public static java.util.Date convertirDateSQLToDateJava(Date dateSql) {
		java.util.Date res = null;
		if(dateSql != null) {
			res = new java.util.Date(dateSql.getTime());
		}
		return res;
	}
	

}
