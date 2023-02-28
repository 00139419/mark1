package com.sv.apppyme.email.repository.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.email.dto.SendEmailDto;
import com.sv.apppyme.email.repository.IEmails;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.ObjectMapperUtils;


@Service
public class SrvEmailImpl implements IEmails{

	Logger log = Logger.getLogger(getClass());
	
	private Properties mProperties;
	private Session mSession;
	private MimeMessage mEmail;
	
	
	@Override
	public SuperGenericResponse sendEmail(SendEmailDto emailInfo) throws AddressException, MessagingException {
		log.info("::::[INICIO]::::[sendEmail]:::: Iniciando servicio para enviar correo::::");
		String json = ObjectMapperUtils.ObjectToJsonString(emailInfo);
		log.info("::::[sendEmail]::::Datos recibidos en JSON: " + json + "::::");
		settingEmail(emailInfo);
		log.info("::::[sendEmail]::::Correo creado correctamente::::value::::" + mEmail.toString() + "::::");
		sendingEmail(emailInfo);
		log.info("::::[FIN]::::[sendEmail]::::Correo enviado correctamente::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	public void settingEmail(SendEmailDto emailInfo) throws AddressException, MessagingException {
		mProperties = new Properties();
		mProperties.put("mail.smtp.host","smtp.gmail.com");
		mProperties.put("mail.smtp.ssl.trust","smtp.gmail.com");
		mProperties.setProperty("mail.smtp.starttls.enable", "true");
		mProperties.setProperty("mail.smtp.port", "587");
		mProperties.setProperty("mail.smtp.user", Constantes.EMAIL_HOST_ISSUER);
		mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		mProperties.setProperty("mail.smtp.auth", "true");
		
		mSession = Session.getDefaultInstance(mProperties);
		mEmail = new MimeMessage(mSession);
		mEmail.setFrom(new InternetAddress(Constantes.EMAIL_HOST_ISSUER));
		mEmail.setRecipient(Message.RecipientType.TO ,new InternetAddress(emailInfo.getEmailReceptor()));
		mEmail.setSubject(emailInfo.getSubject());
		mEmail.setText(emailInfo.getMessage(),"ISO-8859-1","html");
	}
	
	public void sendingEmail(SendEmailDto emailInfo) throws MessagingException {
		Transport mTransport = mSession.getTransport("smtp");
		mTransport.connect(Constantes.EMAIL_HOST_ISSUER, Constantes.EMAIL_HOST_PASSWORD);
		mTransport.sendMessage(mEmail, mEmail.getRecipients(Message.RecipientType.TO));
		mTransport.close();
		
	}
	
	

}
