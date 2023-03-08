package com.sv.apppyme.email.repository.impl;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.email.dto.SendEmailDto;
import com.sv.apppyme.email.dto.SendTokenOTPEmailDto;
import com.sv.apppyme.email.repository.IEmails;
import com.sv.apppyme.email.utils.EmailsUtils;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.ObjectMapperUtils;


@Service
public class SrvEmailImpl implements IEmails{
	
	@Autowired
	ObjectMapper mapper;

	Logger log = Logger.getLogger(getClass());
	
	private Properties mProperties = null;
	private Session mSession = null;
	private MimeMessage mEmail = null;
	private BodyPart messageBodyPart = null;
	
	
	@Override
	public SuperGenericResponse sendEmail(Object emailInfo, String emailType) throws AddressException, MessagingException {
		log.info("::::[INICIO]::::[sendEmail]:::: Iniciando servicio para enviar correo::::");
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);
		String json = ObjectMapperUtils.ObjectToJsonString(emailInfo);
		log.info("::::[sendEmail]::::Datos recibidos en JSON: " + json + "::::");
		instaciarProperties();
		switch (emailType) {
		case "EmailText": 
			settingEmail(emailInfo);
			log.info("::::[sendEmail]::::Correo creado correctamente::::value::::" + mEmail.toString() + "::::");
			sendingEmail(emailInfo);
			log.info("::::[FIN]::::[sendEmail]::::Correo enviado correctamente::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			break;
		case "TokenOTP": 
			settingTokenOTPEmail(emailInfo);
			log.info("::::[sendEmail]::::Correo creado correctamente::::value::::" + mEmail.toString() + "::::");
			sendingEmail(emailInfo);
			log.info("::::[FIN]::::[sendEmail]::::Correo enviado correctamente::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + emailType);
		}
		
		return res;
	}
	
	public void sendingEmail(Object info) throws MessagingException {
		Transport mTransport = mSession.getTransport("smtp");
		mTransport.connect(Constantes.EMAIL_HOST_ISSUER, Constantes.EMAIL_HOST_PASSWORD);
		mTransport.sendMessage(mEmail, mEmail.getRecipients(Message.RecipientType.TO));
		mTransport.close();
		
	}
	
	private void instaciarProperties() {
		mProperties = new Properties();
		mProperties.put("mail.smtp.host","smtp.gmail.com");
		mProperties.put("mail.smtp.ssl.trust","smtp.gmail.com");
		mProperties.setProperty("mail.smtp.starttls.enable", "true");
		mProperties.setProperty("mail.smtp.port", "587");
		mProperties.setProperty("mail.smtp.user", Constantes.EMAIL_HOST_ISSUER);
		mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		mProperties.setProperty("mail.smtp.auth", "true");
		
	}
	
	public void settingEmail(Object info) throws AddressException, MessagingException {
		SendEmailDto emailInfo = (SendEmailDto) info;
		
		mSession = Session.getDefaultInstance(mProperties);
		mEmail = new MimeMessage(mSession);
		mEmail.setFrom(new InternetAddress(Constantes.EMAIL_HOST_ISSUER));
		mEmail.setRecipient(Message.RecipientType.TO ,new InternetAddress(emailInfo.getEmailReceptor()));
		mEmail.setSubject(emailInfo.getSubject());
		mEmail.setText(emailInfo.getMessage(),"ISO-8859-1","html");
		
	}
	
	public void settingTokenOTPEmail(Object info) throws AddressException, MessagingException {
		String json = ObjectMapperUtils.ObjectToJsonString(info);
		
		SendTokenOTPEmailDto emailInfo = null;
		try {
			emailInfo = mapper.readValue(json,SendTokenOTPEmailDto.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String html = EmailsUtils.htmlTokenOTP(emailInfo.getToken(), emailInfo.getExpirationDate().toString());
		
		mSession = Session.getDefaultInstance(mProperties);
		mEmail = new MimeMessage(mSession);
		mEmail.setFrom(new InternetAddress(Constantes.EMAIL_HOST_ISSUER));
		mEmail.setRecipient(Message.RecipientType.TO ,new InternetAddress(emailInfo.getEmailReceptor()));
		mEmail.setSubject(emailInfo.getSubject());
		
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(html, "text/html");
		MimeMultipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(messageBodyPart);
		
		messageBodyPart = new MimeBodyPart();

		messageBodyPart.setText("");
		
		multiPart.addBodyPart(messageBodyPart);
		
		mEmail.setContent(multiPart, "text/html");
		
		
	}
	

}
