package com.sv.apppyme.services.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvUsernameNotFoundException;
import com.sv.apppyme.repository.IUsuario;
import com.sv.apppyme.security.UserDetailsImpl;

@Service
public class srvAuthUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuario userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			Usuario userFound = userDao.selectByUsername(username).getEntity();
			
			if(userFound == null || userFound.getUsername().isBlank() || userFound.getUsername().isEmpty())
				throw new SrvUsernameNotFoundException("Usuario no encontrado");
			
			return new UserDetailsImpl(userFound);
			
		} catch (Exception e) {
			throw new SrvUsernameNotFoundException("Usuario no encontrado!");
		}
	}

}
