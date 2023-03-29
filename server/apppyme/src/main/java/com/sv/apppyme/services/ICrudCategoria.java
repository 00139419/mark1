package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.controllers.dto.CategoriaDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ICrudCategoria {
	
	/**
	 * Metodo que se encarga de insertar Categorias desde el cliente
	 * @param CategoriaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse insertCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de actualizar Categorias desde el cliente
	 * @param CategoriaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse updateCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de eliminar un Categoria desde el cliente
	 * @param CategoriaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse deleteCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener un Categoria por su id Categorias desde el cliente
	 * @param CategoriaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<Categoria> getOneByIDCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException;
	
	/**
	 * Metodo que se encarga de obtener todos Categoria Categorias desde el cliente
	 * @param CategoriaInfo
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	GenericEntityResponse<List<Categoria>> getAllCategoria() throws SrvValidacionException;

}
