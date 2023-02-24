package com.sv.apppyme.services.impl;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.ValidarTokenOTPDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.ITokenOTP;
import com.sv.apppyme.repository.IUsuario;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.services.IToken;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.ObjectMapperUtils;

@Service
public class srvTokenimpl implements IToken {

	@Autowired
	ITokenOTP srvTokenOTP;

	@Autowired
	IUsuario srvUser;
	
	Logger log = Logger.getLogger(getClass());

	//1 hora
	public static int EXP_TOKE_OTP_MILLIS = 1000 * 60 * 60  * 10000;
	
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
		Usuario user = srvUser.selectByUsername("DAnielito2").getEntity();
		tokenOTP.setUsuario(user);
		srvTokenOTP.insert(tokenOTP);
		
		log.info("::::[creaToken]::::LLamando al metodo para guardar tokenOTP::::");
		return res;
	}

	String generarTokenValido() throws SrvValidacionException {
		log.info("::::[Inicio]::::[creaToken]::::Iniciando metodo de genrar TokenOTP::::");
		TokenOTP token = null;
		log.info("::::[creaToken]::::LLamando al metodo para crear y validar TokenOTP::::");
		do {
			String numero = crearNumeroRandom();
			token = (TokenOTP) verificarExistenciaToken(numero);
		} while(!token.getEsValido());
		log.info("::::[creaToken]::::TokenOTP creado y validado exitosamente::::value::::" + token.getToken() + "::::");
		return token.getToken();
	}
	
	String crearNumeroRandom() {
		String numero = "";
		do {
			numero = numero + String.valueOf((int) (Math.random() * 999998 + 1)) + "";
		} while(numero.length() <  6);
		
		if(numero.length() > 6) {
			numero = numero.substring(0, 6);
		}
		return numero;
	}

	TokenOTP verificarExistenciaToken(String numero) throws SrvValidacionException {
		log.info("::::[Inicio]::::[verificarExistenciaToken]::::Iniciando metodo de verificar token::::");
		TokenOTP token = new TokenOTP();
		log.info("::::[verificarExistenciaToken]::::Token creado pero sin verificar::::value::::" + numero + "::::");
		GenericEntityResponse<TokenOTP> res = srvTokenOTP.verificarTokenOTP(numero);
		
		if (res.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de verificar existencia del token");
		
		log.info("::::[verificarExistenciaToken]::::Token creado y verificado::::");
		log.info("::::[verificarExistenciaToken]::::Interpretando respuesta del DAO::::");
		log.info("::::[verificarExistenciaToken]::::Codigo::::" + res.getCodigo());
		log.info("::::[verificarExistenciaToken]::::lista::::" + res.getListaEntity().toString());
		
		
		if(res.getCodigo() == Constantes.SUCCES && res.getListaEntity().isEmpty()) {
			token.setToken(numero);
			token.setEsValido(true);
			log.info("::::[verificarExistenciaToken]::::Token es valido);:::");
		}
		
		Usuario user  = null;
		if(res.getCodigo() == Constantes.SUCCES && !res.getListaEntity().isEmpty()) {
			for (TokenOTP objeto: res.getListaEntity()) {
				token = objeto;
				user = srvUser.selectById(token.getUsuario().getId()).getEntity();
				token.setUsuario(user);
			}
			log.info("::::[verificarExistenciaToken]::::Token existe);:::");
		}
		
		
		
		return token;
	}

	@Override
	public SuperGenericResponse validarToken(ValidarTokenOTPDto tokenOtp) throws SrvValidacionException {
		log.info("::::[INICIO]::::[esTokenVencidoOrValidado]::::Incio de servicio para validar token y activar cuenta::::");
		
		log.info("::::[esTokenVencidoOrValidado]::::Datos recibidoss::::value::::" +  ObjectMapperUtils.ObjectToJsonString(tokenOtp));
		
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "tokenOTP invalido");
		
		TokenOTP token = verificarExistenciaToken(tokenOtp.getToken());
		
		if(token == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error token no encontrado");
		log.info("::::[esTokenVencidoOrValidado]::::Token existe en la base de datos::::");
			
		if(esTokenVencidoOrValidado(token))
			throw new SrvValidacionException(Constantes.ERROR, "Error token invalido");
		log.info("::::[esTokenVencidoOrValidado]::::Token es valido::::");
		
		if(validacionDeToken(token).getCodigo() !=  Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error interno en el proceso de validacion de token");
		log.info("::::[esTokenVencidoOrValidado]::::Se valido el token::::value::::" +  token.getToken() + ":::::");
		
		
		if(activarCuenta(token.getUsuario()).getCodigo() !=  Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error interno en el proceso de activacion de cuenta");
		log.info("::::[esTokenVencidoOrValidado]::::se  activo la  cuenta del usuario::::value::::" + token.getUsuario().getUsername() + "::::");
		
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		log.info("::::[esTokenVencidoOrValidado]::::Fin del  servicio para validar y activar cuenta del usuario::::");
		
		return res;
	}
	
	public Boolean esTokenVencidoOrValidado(TokenOTP token) {
		log.info("::::[INICIO]::::[esTokenVencidoOrValidado]::::Incio de metodo para verificar  si el token es valido y no esta vencido::::");
		
		if(token.getFechaDeVencimiento().before(new Date(System.currentTimeMillis())))
			return true;
		log.info("::::[esTokenVencidoOrValidado]::::Token No esta vencido::::");		
		if(!token.getEsValido())
			return true;
		log.info("::::[esTokenVencidoOrValidado]::::Token es valido::::");
		if(token.getEstaVerificado())
			return true;
		log.info("::::[esTokenVencidoOrValidado]::::Token No ha sido validado::::");
		log.info("::::[FIN]::::[esTokenVencidoOrValidado]::::Fin de metodo::::");
		return false;
	}
	
	
	public SuperGenericResponse activarCuenta(Usuario user) throws SrvValidacionException {
		user.setCuentaActiva(true);
		return srvUser.update(user); 
	}
	
	public SuperGenericResponse validacionDeToken(TokenOTP token) {
		token.setEstaVerificado(true);
		return srvTokenOTP.update(token);
	}

}
