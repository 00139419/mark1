package com.sv.apppyme.services.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.ITokenOTP;
import com.sv.apppyme.repository.IUsuario;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.services.IToken;
import com.sv.apppyme.utils.Constantes;

@Service
public class srvTokenimpl implements IToken {

	@Autowired
	ITokenOTP srvTokenOTP;

	@Autowired
	IUsuario srvUser;
	
	Logger log = Logger.getLogger(getClass());

	//1 hora
	public static int EXP_TOKE_OTP_MILLIS = 1000 * 60 * 60;
	
	@Override
	public TokenDto creaToken(Usuario usuario) throws SrvValidacionException {
		log.info("::::[Inicio]::::[creaToken]::::Iniciando servicio de crearToken::::");
		TokenDto res = null;
		TokenOTP tokenOTP = new TokenOTP();
		tokenOTP.setToken(generarTokenValido());
		tokenOTP.setEsValido(true);
		tokenOTP.setFechaDeCreacion(new Date(System.currentTimeMillis()));
		tokenOTP.setFechaDeVencimiento(new Date(System.currentTimeMillis() + EXP_TOKE_OTP_MILLIS));
		tokenOTP.setEstaVerificado(false);
		Usuario user = srvUser.selectByUsername("Rosa Siguenza").getEntity();
		tokenOTP.setUsuario(user);
		srvTokenOTP.insertToken(tokenOTP);
		
		log.info("::::[creaToken]::::LLamando al metodo para guardar tokenOTP::::");
		//TODO: Guardar tokenOTP
		return res;
	}

	String generarTokenValido() throws SrvValidacionException {
		log.info("::::[Inicio]::::[creaToken]::::Iniciando metodo de genrar TokenOTP::::");
		TokenDto token = null;
		log.info("::::[creaToken]::::LLamando al metodo para crear y validar TokenOTP::::");
		do {
			token = verificarExistenciaToken();
		} while(!token.isEsValido());
		log.info("::::[creaToken]::::TokenOTP creado y validado exitosamente::::value::::" + token.getToken() + "::::");
		return token.getToken();
	}
	
	String crearNumeroRandom() {
		int numero = 0;
		numero = (int) (Math.random() * 999999);
		return String.valueOf(numero);
	}

	TokenDto verificarExistenciaToken() throws SrvValidacionException {
		log.info("::::[Inicio]::::[verificarExistenciaToken]::::Iniciando metodo de crearToken::::");
		TokenDto token = new TokenDto();
		String numero = crearNumeroRandom();
		log.info("::::[verificarExistenciaToken]::::Token creado pero sin verificar::::value::::" + numero + "::::");
		SuperGenericResponse res = srvTokenOTP.verificarTokenOTP(numero);
		if (res.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de verificar existencia del token");
		log.info("::::[verificarExistenciaToken]::::Token creado y verificado::::");
		log.info("::::[verificarExistenciaToken]::::Interpretando respuesta del DAO::::");
		log.info("::::[verificarExistenciaToken]::::Codigo::::" + res.getCodigo());
		log.info("::::[verificarExistenciaToken]::::Mesaje::::" + res.getMensaje());
		if(res.getCodigo() == Constantes.SUCCES && res.getMensaje().equals(Constantes.OK)) {
			token.setToken(numero);
			token.setEsValido(true);
			log.info("::::[verificarExistenciaToken]::::Token es valido);:::");
		}else {
			token.setEsValido(false);
			log.info("::::[verificarExistenciaToken]::::Token NO es valido::::");
			log.info("::::[verificarExistenciaToken]::::Reiniciando proceso de crear y verificar token::::");
		}
		
		return token;
	}

}
