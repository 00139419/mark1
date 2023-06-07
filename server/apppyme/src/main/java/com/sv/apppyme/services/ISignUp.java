package com.sv.apppyme.services;


import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ISignUp {
	
	/**
	 * 
	 * Metodo que se encagar de registar un nuevo usuario.
	 * - Primero valida que el usuario no exista en la base de datos
	 * - Despues obtiene el catalogo del rol que tiene ese usuario
	 * - Despues crea un objeto usuario que se va a mandar a insertar en la base de datos
	 * - Y por ultimo crear un formuario FV que servira para la documentacion de la contratacioa del usuario
	 * @param userInfo
	 * @return una respuesta generia con el estado de todas las transacciones que se realizar para regisrar un nuevo usuario
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse registrarUsuario(UsuarioDto userInfo) throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encagar de obtener un usuario de la base de dato, a traves de su username 
	 * @param userInfo
	 * @return una respuesta de entidad con el usuario encontrado
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	GenericEntityResponse<User> obtenerUsuarioByUsername(UsuarioDto userInfo) throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encagar de verificar si la cuenta esta activa, 
	 * donde un codigo cero significa cuenta activa 
	 * y un codigo -1 significa cuenta inactiva
	 * @param username
	 * @return una respuesta generica con la informacion de la cuenta
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse esCuentaActiva(String username) throws SrvValidacionException;
}
