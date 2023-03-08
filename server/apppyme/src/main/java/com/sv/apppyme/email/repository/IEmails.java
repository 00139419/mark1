package com.sv.apppyme.email.repository;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.email.dto.SendEmailDto;

public interface IEmails {
	
	SuperGenericResponse sendEmail(Object emailInfo, String emailType) throws AddressException, MessagingException;

}
