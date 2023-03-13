package com.sv.apppyme.controllers;

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

import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.services.ITokenOTP;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlData {
	
	@Autowired
	ISignUp srvDataImpl;
	
	@Autowired
	ITokenOTP srvToken;
	
	Logger log = Logger.getLogger(CtrlData.class);
	
	@GetMapping(value = "getAllRoles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericEntityResponse<List<Rol>>> getAllRoles(){
		log.info("***************** Inicio Servicio Obtener Roles *****************");
		log.info("::::[INCIO]::::[getAllRoles]::::Iniciando controlador de data::::");
		GenericEntityResponse<List<Rol>> resController = new GenericEntityResponse<>();
		try {
			resController = srvDataImpl.getAllRoles();
			log.info("::::[FIN]::::[getAllRoles]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<List<Rol>>>(resController, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllRoles]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<List<Rol>>>( new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio Obtener Roles *****************");
		}
	}
	
	@PostMapping(value = "insertar/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperGenericResponse> insetarNuevoUsuario(@RequestBody UsuarioDto userInfo){
		log.info("***************** Inicio Servicio insertar Usuario *****************");
		log.info("::::[INCIO]::::[insetarNuevoUsuario]::::Iniciando controlador de data::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			res = srvDataImpl.insertarUsuario(userInfo);
			log.info("::::[FIN]::::[insetarNuevoUsuario]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insetarNuevoUsuario]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio inserta Usuario *****************");
		}
	}
	
	@PostMapping(value = "obtener/usuarioByUsername", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericEntityResponse<Usuario>> obtenerUsuarioByUsername(@RequestBody UsuarioDto userInfo){
		log.info("***************** Inicio Servicio obtener Usuario por username *****************");
		log.info("::::[INCIO]::::[obtenerUsuarioByUsername]::::Iniciando controlador de data::::");
		GenericEntityResponse<Usuario> res = new GenericEntityResponse<>();
		try {
			res = srvDataImpl.obtenerUsuarioByUsername(userInfo);
			log.info("::::[FIN]::::[obtenerUsuarioByUsername]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<Usuario>>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[obtenerUsuarioByUsername]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<Usuario>>(new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin Servicio obtener Usuario by Username *****************");
		}
	}
	

}
