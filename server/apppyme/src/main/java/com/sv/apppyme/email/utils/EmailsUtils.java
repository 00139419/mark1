package com.sv.apppyme.email.utils;

public class EmailsUtils {

	public static String htmlTokenOTP(String token, String expirationDate) {

		String html = "<div>"
				+ "<div style=\"border:1px solid black;border-radius:5px;width: 700px; height: 330px\">"
				+ "<div style=\"display:flex;align-items:center\">"
				+ "<img"
				+ "src=\"https://ci6.googleusercontent.com/proxy/qqyukdQ-vY-yymzjuGdu43giA5UZIAiGoAV8x1mIQ7DNISEJuPoxe8kyTXXWV_9Y9LE6hmeb5QWorQ0dzGrNUAlG67L_4C6DSLojoUYxAaE=s0-d-e1-ft#http://content.baccredomatic.com/es/alertas/logo/bac-logo.png\""
				+ "style=\"width: 40px; position: relative; left: 10px\""
				+ "alt=\"Logo de AppPyme\""
				+ "/>"
				+ "<h1"
				+ "style=\"font-size: 21px; padding: 0px; position: relative; left: 10px\""
				+ ">"
				+ "AppPyme"
				+ "</h1>"
				+ "</div>"
				+ "<div>"
				+ "<div>"
				+ "<p style=\"font-size: 20px; position: relative; left: 320px\">TokenOTP:</p>"
				+ "</div>"
				+ "<div\">"
				+ "<p style=\"font-size: 60px; font-weight: 900;  position: relative; left: 270px\">"
				+ "123 456"
				+ "</p>"
				+ "</div>"
				+ "</div>"
				+ "<div>"
				+ "<p style=\"position: relative; font-size: 15px; left: 10px;\">"
				+ "Token expires in: Sat Mar 04 16:11:04 CST 2023</p>"
				+ "</div>"
				+ "</div>"
				+ "</div>";
		


		return html;
	}

	public static String darFormatoTokenHTML(String token) {
		return token.substring(0, 3) + " " + token.substring(3, 6);
	}

	public static final String DIR_IMG_TOKEN_OTP_EMAIL = "/Users/dm420/Documents/git/mark1/server/apppyme/src/main/java/com/sv/apppyme/email/utils/logologo.png";
	
	
	
}
