package com.sv.apppyme.utils;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.sv.apppyme.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {
	
	public static SignatureAlgorithm ALGORTMO_DE_CIFRADO = SignatureAlgorithm.HS256;
	
	
	public String generarToken(User userInfo){
		
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
								  .setSubject(userInfo.getEmail())
								  .setIssuedAt(new Date(System.currentTimeMillis()))
								  .setExpiration(new Date(System.currentTimeMillis() + Constantes.JWT_EXP_TIME_MILLIS))
								  .signWith(ALGORTMO_DE_CIFRADO, Constantes.JWT_SECRETKEY);
			 		
			return tokenJWT.compact();
		} catch (Exception e) {
			return "";
		}
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(Constantes.JWT_SECRETKEY)
				.parseClaimsJws(token)
				.getBody();
		
		String subject = claims.getSubject();
		
		return new UsernamePasswordAuthenticationToken(subject,  null, Collections.emptyList());
	}
	
	public String obtenerSubjectByToken(String tokenJWT){
		try {
			final Claims claims = Jwts.parser()
					.setSigningKey(Constantes.JWT_SECRETKEY)
					.parseClaimsJws(tokenJWT)
					.getBody();
			
			return claims.getSubject();
		} catch (JwtException e) {
			return null;
		}
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
