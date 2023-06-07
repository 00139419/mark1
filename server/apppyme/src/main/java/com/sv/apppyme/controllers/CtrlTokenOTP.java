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
import com.sv.apppyme.controllers.dto.ValidarTokenOTPDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.services.ITokenOTP;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(Constantes.ROOT_CTRL)
public class CtrlTokenOTP {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	ISignUp srvDataImpl;
	
	@Autowired
	ITokenOTP srvToken;
	
	@PostMapping(value = "/crear/tokenOTP", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> crearTokenOTP(@RequestBody TokenDto tokenInfo){
		log.info("***************** Inicio Servicio crear tokenOTP *****************");
		log.info("::::[INCIO]::::[crearTokenOTP]::::Iniciando controlador de data::::");
		SuperGenericResponse res = new SuperGenericResponse();
		TokenDto resSer = new TokenDto();
		try {
			if(tokenInfo.getEmail() !=  null) {
				resSer = srvToken.creaTokenOTP(new User(tokenInfo.getEmail()));
				
			}else {
				resSer = srvToken.creaTokenOTP(new User());
			}
			res.setCodigo(resSer.getCodigo());
			res.setMensaje(resSer.getMensaje());
			log.info("::::[FIN]::::[crearTokenOTP]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[crearTokenOTP]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new TokenDto(e.getCodigo(), e.getMensaje()), HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin Servicio crear tokenOTP *****************");
		}
	}
	
	@PostMapping(value = "/validar/tokenOTP", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> validarTokenOTP(@RequestBody ValidarTokenOTPDto tokenInfo){
		log.info("***************** Inicio Servicio validar tokenOTP *****************");
		log.info("::::[INCIO]::::[validarTokenOTP]::::Iniciando controlador de data::::");
		SuperGenericResponse res = null;
		try {
			log.info("::::[FIN]::::[validarTokenOTP]::::fin controlador de data::::");
			res = srvToken.validarToken(tokenInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[validarTokenOTP]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new TokenDto(e.getCodigo(), e.getMensaje()), HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin Servicio validar tokenOTP *****************");
		}
	}

}
