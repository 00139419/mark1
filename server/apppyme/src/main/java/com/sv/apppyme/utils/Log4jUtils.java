package com.sv.apppyme.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Log4jUtils {
	
	/**
	 * @return retorna el stacktrace que se genera cuando ocurre un error
	 * @author dm420
	 */
	public static String getStackTrace(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}

}
