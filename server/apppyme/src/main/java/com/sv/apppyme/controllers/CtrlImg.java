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

import com.sv.apppyme.controllers.dto.ImgDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.ICrudImg;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlImg {


	Logger log = Logger.getLogger(getClass());

	@Autowired
	ICrudImg crudImgimpl;

	@PostMapping(value = "/insert/img", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> insertImg(@RequestBody ImgDto ImgInfo) {
		log.info("***************** Inicio insertar Img *****************");
		SuperGenericResponse res;
		try {
			res = crudImgimpl.insertImg(ImgInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[insertImg]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.OK);
		} finally {
			log.info("***************** Fin insertar Img *****************");
		}
	}

	@PostMapping(value = "/update/img", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> updateImg(@RequestBody ImgDto ImgInfo) {
		log.info("***************** Inicio actualizar Img *****************");
		SuperGenericResponse res;
		try {
			res = crudImgimpl.updateImg(ImgInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[updateImg]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.OK);
		} finally {
			log.info("***************** Fin actualizar Img *****************");
		}
	}

	@PostMapping(value = "/delete/img", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> deleteImg(@RequestBody ImgDto ImgInfo) {
		log.info("***************** Inicio eliminar Img *****************");
		SuperGenericResponse res;
		try {
			res = crudImgimpl.deleteImg(ImgInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[deleteImg]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.OK);
		} finally {
			log.info("***************** Fin eliminar Img *****************");
		}
	}

	@PostMapping(value = "/getOneById/img", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getOneByIdImg(@RequestBody ImgDto ImgInfo) {
		log.info("***************** Inicio obtener por id Img *****************");
		GenericEntityResponse<Img> res;
		try {
			res = crudImgimpl.getOneByIDImg(ImgInfo);
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getOneByIdImg]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.OK);
		} finally {
			log.info("***************** Fin obtener por id Img *****************");
		}
	}

	@PostMapping(value = "/getAll/img", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SuperGenericResponse> getAllImg() {
		log.info("***************** Inicio obtener todos los Img *****************");
		GenericEntityResponse<List<Img>> res;
		try {
			res = crudImgimpl.getAllImg();
			return new ResponseEntity<SuperGenericResponse>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[getAllImg]::::fin controlador de data::::");
			return new ResponseEntity<SuperGenericResponse>(new SuperGenericResponse(e.getCodigo(), e.getMensaje()),
					HttpStatus.OK);
		} finally {
			log.info("***************** Fin obtener todos los Img *****************");
		}
	}
}
