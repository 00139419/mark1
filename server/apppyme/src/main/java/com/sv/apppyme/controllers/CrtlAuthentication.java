package com.sv.apppyme.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IAuth;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CrtlAuthentication {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IAuth srvAuthImpl;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericEntityResponse<TokenDto>> login(@RequestBody UsuarioDto userInfo){
		log.info("***************** Inicio Servicio Authentication *****************");
		log.info("::::[INCIO]::::[login]::::Iniciando controlador de auth::::");
		GenericEntityResponse<TokenDto> res = new GenericEntityResponse<>();
		try {
			res = srvAuthImpl.login(userInfo);
			log.info("::::[FIN]::::[login]::::Iniciando controlador de auth::::");
			return new ResponseEntity<GenericEntityResponse<TokenDto>>(res,HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]::::[login]::::Iniciando controlador de auth::::");
			return new ResponseEntity<GenericEntityResponse<TokenDto>>(new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Inicio Servicio Authentication *****************");
		}
	}
}
