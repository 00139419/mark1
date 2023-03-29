package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.DesarrolladoraDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICrudDesarrolladora {

	/**
	 * Metodo que se encarga de insertar Desarrolladora desde el cliente
	 * @param desarrolladoraInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse insertDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de actualizar Desarrolladora desde el cliente
	 * @param desarrolladoraInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse updateDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de eliminar un Categoria desde el cliente
	 * @param desarrolladoraInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse deleteDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener un Categoria por su id Desarrolladora desde el cliente
	 * @param desarrolladoraInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<Desarrolladora> getOneByIDDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener todos Categoria Desarrolladora desde el cliente
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<List<Desarrolladora>> getAllDesarrolladora() throws SrvValidacionException;
}
