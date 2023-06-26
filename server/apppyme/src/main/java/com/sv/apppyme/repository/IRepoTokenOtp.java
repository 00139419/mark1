package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;

public interface IRepoTokenOtp {
	
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con la existencia del token en la base de datos
	 */
	GenericEntityResponse<TokenOTP> verificarTokenOTP(String numero);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion INSERT en la base de datos
	 */
	SuperGenericResponse insert(TokenOTP tokenOTP);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion UPDATE en la base de datos para cierto registro
	 */
	SuperGenericResponse update(TokenOTP tokenOTP);
	
	/**
	 * 
	 * @author dm420
	 * @return todos los registros de la tabla en la base de datos
	 */
	SuperGenericResponse delete(TokenOTP tokenOTP);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion DELETE en la base de datos para cierto registro
	 */
	GenericEntityResponse<List<TokenOTP>> getAll();

}
