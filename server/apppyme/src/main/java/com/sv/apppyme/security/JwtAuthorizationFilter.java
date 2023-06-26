package com.sv.apppyme.security;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sv.apppyme.repository.impl.DaoUserImpl;
import com.sv.apppyme.services.impl.srvAuthUserDetailsServiceImpl;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.TokenManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	srvAuthUserDetailsServiceImpl srvUserDetails;

	@Autowired
	TokenManager tokenManager;

	@Autowired
	DaoUserImpl userDao;


	Logger log = Logger.getLogger(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String bearerToken = request.getHeader(Constantes.HEADER_AUTHORIZATION_KEY);
		
		if(bearerToken != null && bearerToken.startsWith(Constantes.TOKEN_BEARER_PREFIX)) {
			String token = bearerToken.replace(Constantes.TOKEN_BEARER_PREFIX, "");
			UsernamePasswordAuthenticationToken usernamePAT = tokenManager.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernamePAT);
		}
		
		filterChain.doFilter(request, response);		

	}

}
