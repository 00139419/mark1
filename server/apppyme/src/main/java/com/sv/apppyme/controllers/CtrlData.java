package com.sv.apppyme.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlData {
	
	@Autowired
	IData srvDataImpl;
	
	Logger log = Logger.getLogger(CtrlData.class);
	
	@GetMapping(value = "/getAllRoles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericEntityResponse<List<Rol>>> getAllRoles(){
		log.info("::::[INCIO]::::[getAllRoles]::::Iniciando controlador de data::::");
		GenericEntityResponse<List<Rol>> resController = new GenericEntityResponse<>();
		try {
			resController = srvDataImpl.getAllRoles();
			log.info("::::[FIN]::::[getAllRoles]::::Iniciando controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<List<Rol>>>(resController, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllRoles]::::Iniciando controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<List<Rol>>>( new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		}
	}

}
