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
import com.sv.apppyme.controllers.dto.FacturaResDto;
import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.GenericEntityResponse;
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
	
	@PostMapping(value = "/buy/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<GenericEntityResponse<FacturaResDto>> comprarVideojuego(@RequestBody ComprarVideojuegoReqDto compraInfo){
		log.info("***************** Inicio comprar Videojuego *****************");
		GenericEntityResponse<FacturaResDto> res;
		try {
			res = srvComprarVideoJuegoImpl.comprarVideojuego(compraInfo);
			return new ResponseEntity<GenericEntityResponse<FacturaResDto>>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[comprarVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<FacturaResDto>>(new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin comprar Videojuego *****************");
		}
	}
	
	@PostMapping(value = "/insert/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> insertVideojuego(@RequestBody VideoJuegoDto videojuegoInfo){
		log.info("***************** Inicio insertar Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvCrudVideojuegoImpl.insertVideojuego(videojuegoInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insertVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin insertar Videojuego *****************");
		}
	}
	
	@PostMapping(value = "/update/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> updateVideojuego(@RequestBody VideoJuegoDto videojuegoInfo){
		log.info("***************** Inicio actualizar Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvCrudVideojuegoImpl.UpdateVideojuego(videojuegoInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[updateVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin actualizar Videojuego *****************");
		}
	}
	
	@PostMapping(value = "/delete/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> deleteVideojuego(@RequestBody VideoJuegoDto videojuegoInfo){
		log.info("***************** Inicio eliminar Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvCrudVideojuegoImpl.DeleteVideojuego(videojuegoInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[deleteVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin eliminar Videojuego *****************");
		}
	}
	
	@PostMapping(value = "/getOneById/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getOneByIdVideojuego(@RequestBody VideoJuegoDto videojuegoInfo){
		log.info("***************** Inicio obtener por id Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvCrudVideojuegoImpl.getOneByIDVideojuego(videojuegoInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getOneByIdVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin obtener por id Videojuego *****************");
		}
	}
	
	@PostMapping(value = "/getAll/videojuego", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getAllVideojuego(){
		log.info("***************** Inicio obtener todos los Videojuego *****************");
		SuperGenericResponse res;
		try {
			res = srvCrudVideojuegoImpl.getAllVideojuego();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin obtener todos los Videojuego *****************");
		}
	}
	
	
}
