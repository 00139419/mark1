package com.sv.apppyme.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class Encriptacion {
	
	public static String encriptar(String textoAEncriptar) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
		md.update(textoAEncriptar.getBytes());
		byte[] digest = md.digest();
		byte[] encoded = Base64.encodeBase64(digest);
		return (new String(encoded));
	}
}
