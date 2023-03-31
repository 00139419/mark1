package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.ImgDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICrudImg {
	/**
	 * Metodo que se encarga de insertar Img desde el cliente
	 * @param imgInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse insertImg(ImgDto imgInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de actualizar Img desde el cliente
	 * @param imgInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse updateImg(ImgDto imgInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de eliminar un Img desde el cliente
	 * @param imgInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse deleteImg(ImgDto imgInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener un Img por su id Img desde el cliente
	 * @param imgInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<Img> getOneByIDImg(ImgDto imgInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener todas las Img desde el cliente
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<List<Img>> getAllImg() throws SrvValidacionException;
}
