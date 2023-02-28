package com.sv.apppyme.controllers;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.controllers.dto.EncriptarDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.email.dto.SendEmailDto;
import com.sv.apppyme.email.repository.IEmails;
import com.sv.apppyme.utils.CodigosErrorManager;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.encriptacion.MD5;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlTest {
	
	@Autowired
	IEmails srvEmails;
	
	Logger log = Logger.getLogger(getClass());

	@PostMapping(value = "md5/encriptar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> encriptar(@RequestBody EncriptarDto texto){
		log.info("***************** Inicio test encriptacion *****************");
		log.info("::::[INCIO]::::[encriptar]::::Iniciando controlador de test::::");
		log.info("::::[INCIO]::::[encriptar]::::Datos recibidos::::value::::" + texto.getTexto() + "::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(MD5.encriptar(texto.getTexto()));
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
	
	@PostMapping(value = "email/test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> sendEmails(@RequestBody SendEmailDto emailInfo){
		log.info("***************** Inicio test enviar correos  *****************");
		log.info("::::[INCIO]::::[sendEmails]::::Iniciando controlador de test::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res = srvEmails.sendEmail(emailInfo);
			log.info("::::[FIN]::::[sendEmails]::::Fin controlador de test enviar correo::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
			log.info("Error: " + e.getMessage());
			e.printStackTrace();
			log.info("::::[FIN]::::[ERRROR]::::[sendEmails]::::Error generico::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} finally {
			log.info("***************** Fin test *****************");
		}
	}
	
}
