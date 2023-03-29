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

import com.sv.apppyme.controllers.dto.CategoriaDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ICrudCategoria;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlCategoria {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	ICrudCategoria crudCategoriaimpl;
	
	@PostMapping(value = "/insert/categoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> insertCategoria(@RequestBody CategoriaDto categoriaInfo){
		log.info("***************** Inicio insertar Categoria *****************");
		SuperGenericResponse res;
		try {
			res = crudCategoriaimpl.insertCategoria(categoriaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insertCategoria]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin insertar Categoria *****************");
		}
	}
	
	@PostMapping(value = "/update/categoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> updateCategoria(@RequestBody CategoriaDto categoriaInfo){
		log.info("***************** Inicio actualizar Categoria *****************");
		SuperGenericResponse res;
		try {
			res = crudCategoriaimpl.updateCategoria(categoriaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[updateCategoria]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin actualizar Categoria *****************");
		}
	}
	
	@PostMapping(value = "/delete/categoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> deleteCategoria(@RequestBody CategoriaDto categoriaInfo){
		log.info("***************** Inicio eliminar Categoria *****************");
		SuperGenericResponse res;
		try {
			res = crudCategoriaimpl.deleteCategoria(categoriaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[deleteCategoria]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin eliminar Categoria *****************");
		}
	}
	
	@PostMapping(value = "/getOneById/categoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getOneByIdCategoria(@RequestBody CategoriaDto categoriaInfo){
		log.info("***************** Inicio obtener por id Categoria *****************");
		GenericEntityResponse<Categoria> res;
		try {
			res = crudCategoriaimpl.getOneByIDCategoria(categoriaInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getOneByIdCategoria]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin obtener por id Categoria *****************");
		}
	}
	
	@PostMapping(value = "/getAll/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getAllCategoria(){
		log.info("***************** Inicio obtener todos los Categoria *****************");
		GenericEntityResponse<List<Categoria>> res;
		try {
			res = crudCategoriaimpl.getAllCategoria();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllCategoria]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()), HttpStatus.OK);
		} finally {
			log.info("***************** Fin obtener todos los Categoria *****************");
		}
	}
	

}
