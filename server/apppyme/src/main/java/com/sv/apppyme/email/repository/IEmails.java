package com.sv.apppyme.email.repository;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.email.dto.SendEmailDto;

public interface IEmails {
	
	SuperGenericResponse sendEmail(SendEmailDto emailInfo) throws AddressException, MessagingException;

}
