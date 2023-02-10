package com.sv.apppyme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IAuth;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CrtlAuthentication {
	
	@Autowired
	IAuth srvAuthImpl;

	@PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> login(@RequestBody UsuarioDto userInfo){
		SuperGenericResponse res;
		try {
			res = srvAuthImpl.login(userInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		}
	}
}
