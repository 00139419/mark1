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
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IComprarVideoJuego;
import com.sv.apppyme.utils.Constantes;

@RestController
@RequestMapping(value = Constantes.ROOT_CTRL)
public class CtrlOperaciones {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	IComprarVideoJuego srvComprarVideoJuegoImpl;

	@PostMapping(value = "/buy/videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<GenericEntityResponse<FacturaResDto>> comprarVideojuego(
			@RequestBody ComprarVideojuegoReqDto compraInfo) {
		log.info("***************** Inicio comprar Videojuego *****************");
		GenericEntityResponse<FacturaResDto> res;
		try {
			res = srvComprarVideoJuegoImpl.comprarVideojuego(compraInfo);
			return new ResponseEntity<GenericEntityResponse<FacturaResDto>>(res, HttpStatus.OK);
		} catch (SrvValidacionException e) {
			log.info("::::[FIN]:::[ERROR]::::[comprarVideojuego]::::fin controlador de data::::");
			return new ResponseEntity<GenericEntityResponse<FacturaResDto>>(
					new GenericEntityResponse<>(e.getCodigo(), e.getMensaje()), HttpStatus.BAD_REQUEST);
		} finally {
			log.info("***************** Fin comprar Videojuego *****************");
		}
	}

}
