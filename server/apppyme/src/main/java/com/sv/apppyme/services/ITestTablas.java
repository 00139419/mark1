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
	SuperGenericResponse testProducto() throws SrvValidacionException;
	
	
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
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Plataforma
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testPlataforma() throws SrvValidacionException;
	
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Desarrolladora
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testDesarrolladora() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Img
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testImg() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla rol
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testRol() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla tokenOtp
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testTokenOtp() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla tokenOtp
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testUser() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Compra
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testCompra() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Compra
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testCabeceraFac() throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de hacer un CRUD automatico,
	 * con el objetivo de brindad un test a los DAOs de la tabla Compra
	 * 
	 * @return una respuesta generica con el status de toda las transacciones del CRUD
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse testDetalleFac() throws SrvValidacionException;
	
}
