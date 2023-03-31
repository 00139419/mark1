package com.sv.apppyme.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.apppyme.controllers.dto.DesarrolladoraDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ICrudDesarrolladora;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlDesarrolladora {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	ICrudDesarrolladora crudDesarrolladoraimpl;

	@PostMapping(value = "/insert/desarrolladora", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> insertDesarrolladora(@RequestBody DesarrolladoraDto DesarrolladoraInfo) {
		log.info("***************** Inicio insertar Desarrolladora *****************");
		SuperGenericResponse res;
		try {
			res = crudDesarrolladoraimpl.insertDesarrolladora(DesarrolladoraInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insertDesarrolladora]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin insertar Desarrolladora *****************");
		}
	}

	@PostMapping(value = "/update/desarrolladora", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> updateDesarrolladora(@RequestBody DesarrolladoraDto DesarrolladoraInfo) {
		log.info("***************** Inicio actualizar Desarrolladora *****************");
		SuperGenericResponse res;
		try {
			res = crudDesarrolladoraimpl.updateDesarrolladora(DesarrolladoraInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[updateDesarrolladora]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin actualizar Desarrolladora *****************");
		}
	}

	@PostMapping(value = "/delete/desarrolladora", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> deleteDesarrolladora(@RequestBody DesarrolladoraDto DesarrolladoraInfo) {
		log.info("***************** Inicio eliminar Desarrolladora *****************");
		SuperGenericResponse res;
		try {
			res = crudDesarrolladoraimpl.deleteDesarrolladora(DesarrolladoraInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[deleteDesarrolladora]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin eliminar Desarrolladora *****************");
		}
	}

	@PostMapping(value = "/getOneById/desarrolladora", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getOneByIdDesarrolladora(@RequestBody DesarrolladoraDto DesarrolladoraInfo) {
		log.info("***************** Inicio obtener por id Desarrolladora *****************");
		GenericEntityResponse<Desarrolladora> res;
		try {
			res = crudDesarrolladoraimpl.getOneByIDDesarrolladora(DesarrolladoraInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getOneByIdDesarrolladora]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin obtener por id Desarrolladora *****************");
		}
	}

	@PostMapping(value = "/getAll/desarrolladora", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getAllDesarrolladora() {
		log.info("***************** Inicio obtener todos los Desarrolladora *****************");
		GenericEntityResponse<List<Desarrolladora>> res;
		try {
			res = crudDesarrolladoraimpl.getAllDesarrolladora();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllDesarrolladora]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin obtener todos los Desarrolladora *****************");
		}
	}

}
