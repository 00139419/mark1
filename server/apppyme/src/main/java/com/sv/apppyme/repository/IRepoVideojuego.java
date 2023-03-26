package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;

public interface IRepoVideojuego {
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion INSERT en la base de datos
	 */
	SuperGenericResponse insert(Videojuego videojuego);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion UPDATE en la base de datos para cierto registro
	 */
	SuperGenericResponse update(Videojuego videojuego);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion DELETE en la base de datos para cierto registro
	 */
	SuperGenericResponse delete(Videojuego videojuego);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return un registro de la base de datos, a traves de su ID
	 */
	GenericEntityResponse<Videojuego> getOneById(int id);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return un registro de la base de datos, a traves de su ID
	 */
	GenericEntityResponse<Videojuego> getOneByNombre(String nombre);
	
	/**
	 * 
	 * @author dm420
	 * @return todos los registros de la tabla en la base de datos
	 */
	GenericEntityResponse<List<Videojuego>> getAll();
}
