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

import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.services.ITokenOTP;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlSignUp {

	@Autowired
	ISignUp srvSignUpImpl;

	@Autowired
	ITokenOTP srvToken;

	Logger log = Logger.getLogger(CtrlSignUp.class);

	@PostMapping(value = "/insertar/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> insetarNuevoUsuario(@RequestBody UsuarioDto userInfo) {
		log.info("***************** Inicio Servicio insertar Usuario *****************");
		log.info("::::[INCIO]::::[insetarNuevoUsuario]::::Iniciando controlador de data::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res = srvSignUpImpl.insertarUsuario(userInfo);
			log.info("::::[FIN]::::[insetarNuevoUsuario]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insetarNuevoUsuario]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio inserta Usuario *****************");
		}
	}


}
