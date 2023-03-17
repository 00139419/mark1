package com.sv.apppyme.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.controllers.dto.EncriptarDto;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.email.dto.SendEmailDto;
import com.sv.apppyme.email.repository.IEmails;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.services.ITestTablas;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.encriptacion.MD5;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL + "/test")
public class CtrlTest {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IEmails srvEmailsImpl;
	
	@Autowired
	ISignUp srvSignUpImpl;
	
	@Autowired
	ITestTablas srvTestTablasImpl;
	
	@PostMapping(value = "/getConnection", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@PostMapping(value = "/encriptar/md", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> encriptar(@RequestBody EncriptarDto texto){
		log.info("***************** Inicio test encriptacion *****************");
		log.info("::::[INCIO]::::[encriptar]::::Iniciando controlador de test::::");
		log.info("::::[INCIO]::::[encriptar]::::Datos recibidos::::value::::" + texto.getTexto() + "::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(MD5.encriptar(texto.getTexto()));
			log.info("::::[FIN]::::[encriptar]::::Fin controlador de test::::");
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
	
	@PostMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> sendEmails(@RequestBody SendEmailDto emailInfo){
		log.info("***************** Inicio test enviar correos  *****************");
		log.info("::::[INCIO]::::[sendEmails]::::Iniciando controlador de test::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			emailInfo.setExpirationDate(new Date());
			res = srvEmailsImpl.sendEmail(emailInfo, emailInfo.getEmailType());
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
	
	@GetMapping(value = "/getAllRoles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericEntityResponse<List<Rol>>> getAllRoles() {
		log.info("***************** Inicio Servicio Obtener Roles *****************");
		log.info("::::[INCIO]::::[getAllRoles]::::Iniciando controlador de test::::");
		GenericEntityResponse<List<Rol>> resController = new GenericEntityResponse<>();
		try {
			resController = srvSignUpImpl.getAllRoles();
			log.info("::::[FIN]::::[getAllRoles]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<List<Rol>>>(resController, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllRoles]::::fin controlador de test::::");
			return new ResponseEntity<GenericEntityResponse<List<Rol>>>(
					new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio Obtener Roles *****************");
		}
	}
	
	@PostMapping(value = "/obtener/usuarioByUsername", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericEntityResponse<Usuario>> obtenerUsuarioByUsername(@RequestBody UsuarioDto userInfo) {
		log.info("***************** Inicio Servicio obtener Usuario por username *****************");
		log.info("::::[INCIO]::::[obtenerUsuarioByUsername]::::Iniciando controlador de test::::");
		GenericEntityResponse<Usuario> res = new GenericEntityResponse<>();
		try {
			res = srvSignUpImpl.obtenerUsuarioByUsername(userInfo);
			log.info("::::[FIN]::::[obtenerUsuarioByUsername]::::fin controlador de test::::");
			return new ResponseEntity<GenericEntityResponse<Usuario>>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[obtenerUsuarioByUsername]::::fin controlador de test::::");
			return new ResponseEntity<GenericEntityResponse<Usuario>>(
					new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio obtener Usuario by Username *****************");
		}
	}
	
	
	@PostMapping(value = "/tablaCategoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> tablaCat() {
		log.info("***************** Inicio Servicio obtener Usuario por username *****************");
		log.info("::::[INCIO]::::[obtenerUsuarioByUsername]::::Iniciando controlador de test::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res = srvTestTablasImpl.testCategoria();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[obtenerUsuarioByUsername]::::fin controlador de test::::");
			return new ResponseEntity<SuperGenericResponse>(
					new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio obtener Usuario by Username *****************");
		}
	}
	
	@PostMapping(value = "/tablaVideoJuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> tablaVideojuego() {
		log.info("***************** Inicio Servicio obtener Usuario por username *****************");
		log.info("::::[INCIO]::::[obtenerUsuarioByUsername]::::Iniciando controlador de test::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res = srvTestTablasImpl.testVideojuego();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[obtenerUsuarioByUsername]::::fin controlador de test::::");
			return new ResponseEntity<SuperGenericResponse>(
					new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio obtener Usuario by Username *****************");
		}
	}
	
	@PostMapping(value = "/tablaReporte", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> tablaReporte() {
		log.info("***************** Inicio Servicio obtener Usuario por username *****************");
		log.info("::::[INCIO]::::[obtenerUsuarioByUsername]::::Iniciando controlador de test::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res = srvTestTablasImpl.testReporte();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[obtenerUsuarioByUsername]::::fin controlador de test::::");
			return new ResponseEntity<SuperGenericResponse>(
					new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio obtener Usuario by Username *****************");
		}
	}
	
	
}
