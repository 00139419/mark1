package com.sv.apppyme.utils;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.sv.apppyme.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {
	
	public static SignatureAlgorithm ALGORTMO_DE_CIFRADO = SignatureAlgorithm.HS256;
	
	
	public String generarToken(Usuario userInfo){
		
		try {
			HashMap<String, Object> header = new HashMap<>();
			header.put("alg", ALGORTMO_DE_CIFRADO.toString());
			header.put("typ", "JWT");
			
			HashMap<String, Object> claims = new HashMap<>();
			claims.put("rol", userInfo.getRol());
			claims.put("secret_claim", Constantes.JWT_SECRET_CLAIMS);
			
			@SuppressWarnings("deprecation")
			JwtBuilder tokenJWT = Jwts.builder()
								  .setHeader(header)
								  .addClaims(claims)
								  .setSubject(userInfo.getUsername())
								  .setIssuedAt(new Date(System.currentTimeMillis()))
								  .setExpiration(new Date(System.currentTimeMillis() + Constantes.JWT_EXP_TIME_MILLIS))
								  .signWith(ALGORTMO_DE_CIFRADO, Constantes.JWT_SECRETKEY);
			 		
			return tokenJWT.compact();
		} catch (Exception e) {
			return "";
		}
	}
	
	public String obtenerSubjectByToken(String tokenJWT){
		final Claims claims = Jwts.parser()
				.setSigningKey(Constantes.JWT_SECRETKEY)
				.parseClaimsJws(tokenJWT)
				.getBody();
		
		return claims.getSubject();
	}
	
	public String obtenerRolByToken(String tokenJWT){
		final Claims claims = Jwts.parser()
				.setSigningKey(Constantes.JWT_SECRETKEY)
				.parseClaimsJws(tokenJWT)
				.getBody();
		
		return claims.get("rol").toString();
	}
	
	public Boolean estaVigenteJWT(String tokenJWT){
		final Claims claims = Jwts.parser()
				.setSigningKey(Constantes.JWT_SECRETKEY)
				.parseClaimsJws(tokenJWT)
				.getBody();
		
		return claims.getExpiration().before(new Date());
	}
	
	public Boolean contieneSecretClaimJWT(String tokenJWT){
		final Claims claims = Jwts.parser()
				.setSigningKey(Constantes.JWT_SECRETKEY)
				.parseClaimsJws(tokenJWT)
				.getBody();
		
		return claims.get("rol").toString().equals(Constantes.JWT_SECRET_CLAIMS);
	}
	
}
