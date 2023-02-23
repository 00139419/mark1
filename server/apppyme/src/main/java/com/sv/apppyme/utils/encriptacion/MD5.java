package com.sv.apppyme.utils.encriptacion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.utils.Constantes;

public class MD5 {
	
	public static String encriptar(String textoAEncriptar) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
		md.update(textoAEncriptar.getBytes());
		byte[] digest = md.digest();
		byte[] encoded = Base64.encodeBase64(digest);
		return (new String(encoded));
	}
	
	public static SuperGenericResponse comparePasswords(String interedpassword, String savedPassword) {
		if(interedpassword.equals(savedPassword)) {
			return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
		}else {
			return new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);
		}
	}
	
	 
}
