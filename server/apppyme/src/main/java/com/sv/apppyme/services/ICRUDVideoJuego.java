package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICRUDVideoJuego {
	
	/**
	 * Metodo que se encarga de insertar videojuegos desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse insertVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de actualizar videojuegos desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse UpdateVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de eliminar un videojuego desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse DeleteVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener un videojuego por su id videojuegos desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<Videojuego> getOneByIDVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener todos videojuego videojuegos desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<List<Videojuego>> getAllVideojuego() throws SrvValidacionException;
	
}
