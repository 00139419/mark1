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

import com.sv.apppyme.controllers.dto.ComprarVideojuegoReqDto;
import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ICRUDVideoJuego;
import com.sv.apppyme.services.IComprarVideoJuego;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlData {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IComprarVideoJuego srvComprarVideoJuegoImpl;
	
	@Autowired
	ICRUDVideoJuego srvCrudVideojuegoImpl;
	
	@PostMapping(value = "/comprar/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> comprarVideojuego(@RequestBody ComprarVideojuegoReqDto compraInfo){
		log.info("***************** Inicio comprar Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvComprarVideoJuegoImpl.comprarVideojuego(compraInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[comprarVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin comprar Videojuego *****************");
		}
	}
	
	@PostMapping(value = "/insetar/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> insertarVideojuego(@RequestBody VideoJuegoDto videojuegoInfo){
		log.info("***************** Inicio insertar Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvCrudVideojuegoImpl.insertVideojuego(videojuegoInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insertarVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin comprar Videojuego *****************");
		}
	}
	
	
}
