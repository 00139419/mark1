package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Producto;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICrudVideoJuego {
	
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
	SuperGenericResponse updateVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de eliminar un videojuego desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse deleteVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener un videojuego por su id videojuegos desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<Producto> getOneByIDVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener todos videojuego videojuegos desde el cliente
	 * @param videojuegoInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<List<Producto>> getAllVideojuego() throws SrvValidacionException;
	
}
