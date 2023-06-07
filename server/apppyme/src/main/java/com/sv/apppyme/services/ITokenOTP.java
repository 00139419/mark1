package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.ValidarTokenOTPDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.exception.SrvValidacionException;

public interface ITokenOTP {

	/**
	 * 
	 * Metodo que se encaga de crear un tokenOTP para el usuario dado
	 * @param usuario
	 * @return un token OTP
	 * @throws SrvValidacionException
	 *  @author dm420
	 */
	TokenDto creaTokenOTP(User usuario) throws SrvValidacionException;
	
	/**
	 * 
	 * Metodo que se encarga de validar un tokenOTP
	 * @param tokenOtp
	 * @return una respuesta generica con el status de la transaccion
	 * @throws SrvValidacionException
	 */
	SuperGenericResponse validarToken(ValidarTokenOTPDto tokenOtp) throws SrvValidacionException;
	
	/**
	 * Metodo que se encaga de eliminar los tokenOTP que ya esten obsoletos de la base de datos
	 * - para eliminar los token vaida que el token el token no haya sido validado antes,
	 *  que el token no este vencido y el token sea valido
	 * @author dm420
	 */
	void eliminarTokensObsoletos();
	
}
