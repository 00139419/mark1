package com.sv.apppyme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sv.apppyme.entities.User;
import com.sv.apppyme.exception.SrvUsernameNotFoundException;
import com.sv.apppyme.repository.IRepoUsuario;
import com.sv.apppyme.security.UserDetailsImpl;

@Service
public class srvAuthUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IRepoUsuario userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			User userFound = userDao.getOneByEmail(username).getEntity();
			
			if(userFound == null || userFound.getEmail().isBlank() || userFound.getEmail().isEmpty())
				throw new SrvUsernameNotFoundException("Usuario no encontrado");
			
			return new UserDetailsImpl(userFound);
			
		} catch (Exception e) {
			throw new SrvUsernameNotFoundException("Usuario no encontrado!");
		}
	}

}
