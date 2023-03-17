package com.sv.apppyme.services;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ITestTablas {
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Categoria
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testCategoria() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla videojuego
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testVideojuego() throws SrvValidacionException;
	
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla videojuego
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testReporte() throws SrvValidacionException;
	
	
}
