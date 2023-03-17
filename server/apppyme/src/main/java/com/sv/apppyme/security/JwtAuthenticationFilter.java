package com.sv.apppyme.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.services.ITokenOTP;
import com.sv.apppyme.services.impl.srvSignUpImpl;
import com.sv.apppyme.services.impl.srvTokenimpl;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.TokenManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	TokenManager tokeManager;
	
	ISignUp srvDaraImpl;
	
	ITokenOTP srvTokenOTP;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		ITokenOTP srvTokenOTP = new srvTokenimpl();
		
		srvTokenOTP.eliminarTokensObsoletos();
		
		AuthCredentials authCredentials = new AuthCredentials();
		
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getUsername(),
				authCredentials.getPassword(),
				Collections.emptyList()
				);
		
		return getAuthenticationManager().authenticate(usernamePAT);
	}

	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		srvTokenOTP = new srvTokenimpl();
		srvDaraImpl = new srvSignUpImpl();
		
		srvTokenOTP.eliminarTokensObsoletos();
		
		tokeManager = new TokenManager();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		
		String username = userDetails.getUsername();
		String password = userDetails.getPassword();
		
		try {
			if(srvDaraImpl.esCuentaActiva(username).getCodigo() ==  Constantes.SUCCES) {
				String token = tokeManager.generarToken(new Usuario(username, password));
				
				response.addHeader("codigo", "0");
				response.addHeader("descripcionm", "OK");
				response.addHeader(Constantes.HEADER_AUTHORIZATION_KEY,Constantes.TOKEN_BEARER_PREFIX + token);
				response.getWriter().flush();
			
				
				super.successfulAuthentication(request, response, chain, authResult);
			}else {
				response.addHeader("codigo", "-2");
				response.addHeader("descripcionm", "Cuenta Inactiva");
				response.getWriter().flush();
				
				super.successfulAuthentication(request, response, chain, authResult);
			}
		} catch (SrvValidacionException e) {
			response.addHeader("codigo", "-1");
			response.addHeader("descripcionm", "Error generico " + e.getMessage());
			response.getWriter().flush();
			
			super.successfulAuthentication(request, response, chain, authResult);
		}
	}
}
