package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.PlataformaDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICrudPlataforma {
	/**
	 * Metodo que se encarga de insertar Plataforma desde el cliente
	 * @param plataformaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse insertPlataforma(PlataformaDto plataformaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de actualizar Plataforma desde el cliente
	 * @param plataformaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse updatePlataforma(PlataformaDto plataformaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de eliminar un Plataforma desde el cliente
	 * @param plataformaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse deletePlataforma(PlataformaDto plataformaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener un Plataforma por su id Plataforma desde el cliente
	 * @param plataformaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<Plataforma> getOneByIDPlataforma(PlataformaDto plataformaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener todas las Plataforma desde el cliente
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<List<Plataforma>> getAllPlataforma() throws SrvValidacionException;
}
