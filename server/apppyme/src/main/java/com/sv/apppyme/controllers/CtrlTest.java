package com.sv.apppyme.controllers;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.controllers.dto.EncriptarDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Encriptacion;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlTest {
	
	Logger log = Logger.getLogger(getClass());

	@PostMapping(value = "encriptar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> encriptar(@RequestBody EncriptarDto texto){
		log.info("***************** Inicio test encriptacion *****************");
		log.info("::::[INCIO]::::[encriptar]::::Iniciando controlador de test::::");
		log.info("::::[INCIO]::::[encriptar]::::Datos recibidos::::value::::" + texto.getTexto() + "::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Encriptacion.encriptar(texto.getTexto()));
			log.info("::::[FIN]::::[encriptar]::::Fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (NoSuchAlgorithmException e) {
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
			log.info("::::[FIN]::::[ERRROR]::::[encriptar]::::Error encripatando los datos::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} finally {
			log.info("***************** Fin test encriptacion *****************");
		}
	}
	
	@PostMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> ComprobarConeccionConLaApi(){
		log.info("***************** Inicio test  *****************");
		log.info("::::[INCIO]::::[ComprobarConeccionConLaApi]::::Iniciando controlador de test::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			log.info("::::[FIN]::::[ComprobarConeccionConLaApi]::::Fin controlador de test::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
			log.info("::::[FIN]::::[ERRROR]::::[ComprobarConeccionConLaApi]::::Error generico::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} finally {
			log.info("***************** Fin test *****************");
		}
	}
}
