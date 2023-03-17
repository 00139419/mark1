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
import com.sv.apppyme.entities.Usuario;
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
	public ResponseEntity<TokenDto> crearTokenOTP(@RequestBody TokenDto tokenInfo){
		log.info("***************** Inicio Servicio crear tokenOTP *****************");
		log.info("::::[INCIO]::::[crearTokenOTP]::::Iniciando controlador de data::::");
		TokenDto res = new TokenDto();
		try {
			if(tokenInfo.getUsername() !=  null) {
				res = srvToken.creaTokenOTP(new Usuario(tokenInfo.getUsername()));
			}else {
				res = srvToken.creaTokenOTP(new Usuario());
			}
			log.info("::::[FIN]::::[crearTokenOTP]::::fin controlador de data::::");
			return new ResponseEntity<TokenDto>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[crearTokenOTP]::::fin controlador de data::::");
			return new ResponseEntity<TokenDto>(new TokenDto(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
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
			return new ResponseEntity<SuperGenericResponse>(new TokenDto(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio validar tokenOTP *****************");
		}
	}

}
