package com.sv.apppyme.utils;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;

import io.jsonwebtoken.*;

@Component
public class TokenManager {
	
	public static SignatureAlgorithm ALGORTMO_DE_CIFRADO = SignatureAlgorithm.HS256;
	
	
	public String generarToken(Usuario userInfo) throws SrvValidacionException{
		
		HashMap<String, Object> header = new HashMap<>();
		header.put("alg", ALGORTMO_DE_CIFRADO.toString());
		header.put("typ", "JWT");
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("rol", userInfo.getRol());
		
		@SuppressWarnings("deprecation")
		JwtBuilder tokenJWT = Jwts.builder()
							  .setHeader(header)
							  .addClaims(claims)
							  .setSubject(userInfo.getUsername())
							  .setIssuedAt(new Date(System.currentTimeMillis()))
							  .setExpiration(new Date(System.currentTimeMillis() + Constantes.JWT_EXP_TIME_MILLIS))
							  .signWith(ALGORTMO_DE_CIFRADO, Constantes.JWT_SECRETKEY);
		 		
		return tokenJWT.compact();
	}
	
}
