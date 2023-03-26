package com.sv.apppyme.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	
	public static java.util.Date convetirStringToDate(String fechaString){
		String FormatoDeFecha = Constantes.FORMATO_DE_FECHA;
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat(FormatoDeFecha);
		java.util.Date date1;
		try {
			date1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse(fechaString);
			java.util.Date fechaUtil = new SimpleDateFormat(FormatoDeFecha).parse(formatoFecha.format(date1));
			return fechaUtil;
		} catch (ParseException e) {
			try {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaString);
				java.util.Date fechaUtil = new SimpleDateFormat(FormatoDeFecha).parse(formatoFecha.format(date1));
				return fechaUtil;
			} catch (ParseException e1) {
				try {
					date1 = new SimpleDateFormat("yyyyMMdd").parse(fechaString);
					java.util.Date fechaUtil = new SimpleDateFormat(FormatoDeFecha).parse(formatoFecha.format(date1));
					return fechaUtil;
				} catch (ParseException e2) {
					try {
						date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
						java.util.Date fechaUtil = new SimpleDateFormat(FormatoDeFecha).parse(formatoFecha.format(date1));
						return fechaUtil;
					} catch (ParseException e3) {
						try {
							date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fechaString);
							java.util.Date fechaUtil = new SimpleDateFormat(FormatoDeFecha).parse(formatoFecha.format(date1));
							return fechaUtil;
						} catch (ParseException e4) {
							System.out.println(
									"--> Error al convertir fecha String to java.sql.Date --->>> [" + fechaString + "]");
							e1.printStackTrace();// 19000101
						}
					}
				}
			}
		}
		
		return null;
	}
	
	
	public static Timestamp convertDateToTimeStamp(java.util.Date date) {
		return new Timestamp(date.getTime());
	}
	
	public static Date convertTimeStampToDate(Timestamp timeStamp) {
		return new Date(timeStamp.getTime());
	}
}
