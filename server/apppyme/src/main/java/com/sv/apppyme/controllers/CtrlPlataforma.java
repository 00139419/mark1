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

import com.sv.apppyme.controllers.dto.PlataformaDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ICrudPlataforma;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlPlataforma {
	

	Logger log = Logger.getLogger(getClass());

	@Autowired
	ICrudPlataforma crudPlataformaimpl;

	@PostMapping(value = "/insert/plataforma", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> insertPlataforma(@RequestBody PlataformaDto PlataformaInfo) {
		log.info("***************** Inicio insertar Plataforma *****************");
		SuperGenericResponse res;
		try {
			res = crudPlataformaimpl.insertPlataforma(PlataformaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insertPlataforma]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin insertar Plataforma *****************");
		}
	}

	@PostMapping(value = "/update/plataforma", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> updatePlataforma(@RequestBody PlataformaDto PlataformaInfo) {
		log.info("***************** Inicio actualizar Plataforma *****************");
		SuperGenericResponse res;
		try {
			res = crudPlataformaimpl.updatePlataforma(PlataformaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[updatePlataforma]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin actualizar Plataforma *****************");
		}
	}

	@PostMapping(value = "/delete/plataforma", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> deletePlataforma(@RequestBody PlataformaDto PlataformaInfo) {
		log.info("***************** Inicio eliminar Plataforma *****************");
		SuperGenericResponse res;
		try {
			res = crudPlataformaimpl.deletePlataforma(PlataformaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[deletePlataforma]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin eliminar Plataforma *****************");
		}
	}

	@PostMapping(value = "/getOneById/plataforma", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getOneByIdPlataforma(@RequestBody PlataformaDto PlataformaInfo) {
		log.info("***************** Inicio obtener por id Plataforma *****************");
		GenericEntityResponse<Plataforma> res;
		try {
			res = crudPlataformaimpl.getOneByIDPlataforma(PlataformaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getOneByIdPlataforma]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin obtener por id Plataforma *****************");
		}
	}

	@PostMapping(value = "/getAll/plataforma", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getAllPlataforma() {
		log.info("***************** Inicio obtener todos los Plataforma *****************");
		GenericEntityResponse<List<Plataforma>> res;
		try {
			res = crudPlataformaimpl.getAllPlataforma();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllPlataforma]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin obtener todos los Plataforma *****************");
		}
	}

}
