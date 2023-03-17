package com.sv.apppyme.email.repository;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.sv.apppyme.dto.SuperGenericResponse;

public interface IEmails {
	
	/**
	 * 
	 * Metodo que se encargaa de enviar correos electronicos, a traves del protocolo smtp utilizando el host de gmail
	 * 
	 * @param emailInfo
	 * @param emailType
	 * @return respuesta generica con el status del envio de correos electronicos
	 * @throws AddressException
	 * @throws MessagingException
	 * @author dm420
	 */
	SuperGenericResponse sendEmail(Object emailInfo, String emailType) throws AddressException, MessagingException;

}
