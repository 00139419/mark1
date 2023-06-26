package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.User;

public interface IRepoUser {

	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion INSERT en la base de datos
	 */
	SuperGenericResponse insert(User usuario); 
	
	/**
	 * 
	 * @param asd
	 * @author dm420
	 * @return un registro de la base de datos, a traves de su Username
	 */
	GenericEntityResponse<User> getOneByEmail(String username);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return un registro de la base de datos, a traves de su ID
	 */
	GenericEntityResponse<User> getOneById(int id);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion UPDATE en la base de datos para cierto registro
	 */
	SuperGenericResponse update(User usuario);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return un todos los registro de la base de datos
	 */
	GenericEntityResponse<List<User>> getAll();
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion INSERT en la base de datos
	 */
	SuperGenericResponse delete(User usuario); 
	
	
}
