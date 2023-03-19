package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICRUDVideoJuego {
	SuperGenericResponse insertVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	SuperGenericResponse UpdateVideojuego() throws SrvValidacionException;
	SuperGenericResponse DeleteVideojuego() throws SrvValidacionException;
	SuperGenericResponse getOneByIDVideojuego() throws SrvValidacionException;
	
}
