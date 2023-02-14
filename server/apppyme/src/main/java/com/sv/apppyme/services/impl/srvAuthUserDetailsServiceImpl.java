package com.sv.apppyme.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sv.apppyme.dao.IUsuarioDao;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvUsernameNotFoundException;

@Service
public class srvAuthUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioDao userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario userFound = userDao.selectByUsername(username).getEntity();
		
		if(userFound == null || userFound.getUsername().isBlank() || userFound.getUsername().isEmpty())
			throw new SrvUsernameNotFoundException("Usuario no encontrado");
		
		return new org.springframework.security.core.userdetails.User(
				userFound.getUsername(),
				userFound.getPassword(),
				new ArrayList<>()
				);
	}

}
