package com.sv.apppyme.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sv.apppyme.dao.impl.UsuarioDaoImpl;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.services.impl.srvAuthUserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	srvAuthUserDetailsServiceImpl srvUserDetails;

	@Autowired
	TokenManager tokenManager;

	@Autowired
	UsuarioDaoImpl userDao;

	Logger log = Logger.getLogger(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenHeader = null;
		String token = null;
		String username = null;

		tokenHeader = request.getHeader(Constantes.HEADER_AUTHORIZATION_KEY);

		if (tokenHeader != null || tokenHeader.startsWith(Constantes.TOKEN_BEARER_PREFIX)) {
			token = tokenHeader.substring(7);
			try {
				username = tokenManager.obtenerSubjectByToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT TOKEN has expired");
			} catch (MalformedJwtException e) {
				System.out.println("JWT Malformado");
			}

		} else {
			log.info(":::::[SpringSecurity]::: token no encontrado ::::");
		}

		if (username != null || SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDatilsObject = srvUserDetails.loadUserByUsername(username);
				Usuario userFound = userDao.selectByUsername(username).getEntity();
				if (userFound != null) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							userDatilsObject, null, userDatilsObject.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Error en la verificacion del token");
				log.info("Mensaje: " + e.getMessage());
				log.info(e.getStackTrace());
			}
		}
		
		filterChain.doFilter(request, response);
		

	}

}
