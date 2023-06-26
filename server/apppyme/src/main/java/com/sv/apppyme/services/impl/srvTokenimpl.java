package com.sv.apppyme.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.JwtRes;
import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.ValidarTokenOTPDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoTokenOtp;
import com.sv.apppyme.repository.IRepoUser;
import com.sv.apppyme.repository.impl.DaoTokenOtpImpl;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.ObjectMapperUtils;
import com.sv.apppyme.utils.TokenManager;

@Service
public class srvTokenimpl implements com.sv.apppyme.services.ITokenOTP {

	@Autowired
	IRepoTokenOtp srvTokenOTP;

	@Autowired
	IRepoUser userDao;
	
	@Autowired
	TokenManager tokenManager;

	Logger log = Logger.getLogger(getClass());

	// 1 hora
	public static int EXP_TOKE_OTP_MILLIS = 1000 * 60 * 60 * 10000;

	@Override
	public TokenDto creaTokenOTP(User usuario) throws SrvValidacionException {
		log.info("::::[Inicio]::::[creaToken]::::Iniciando servicio de crearToken::::");
		log.info("::::[Inicio]::::[creaToken]::::Datos Recibidos::::value::::" + ObjectMapperUtils.ObjectToJsonString(usuario));
		
		TokenDto res = new TokenDto();
		TokenOTP tokenOTP = new TokenOTP();
		tokenOTP.setToken(generarTokenValido());
		tokenOTP.setEsValido(true);
		tokenOTP.setFechaDeCreacion(new Date(System.currentTimeMillis()));
		tokenOTP.setFechaDeVencimiento(new Date(System.currentTimeMillis() + EXP_TOKE_OTP_MILLIS));
		tokenOTP.setEstaVerificado(false);
		
		if(usuario.getEmail() == null)
			usuario = userDao.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getEntity();
		
		if(usuario.getEmail() !=  null)
			usuario = userDao.getOneByEmail(usuario.getEmail()).getEntity();
		
		tokenOTP.setUsuario(usuario);
			
		srvTokenOTP.insert(tokenOTP);
		
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	String generarTokenValido() throws SrvValidacionException {
		log.info("::::[Inicio]::::[creaToken]::::Iniciando metodo de genrar TokenOTP::::");
		TokenOTP token = null;
		log.info("::::[creaToken]::::LLamando al metodo para crear y validar TokenOTP::::");
		do {
			String numero = crearNumeroRandom();
			token = (TokenOTP) verificarExistenciaToken(numero);
		} while (!token.getEsValido());
		log.info("::::[creaToken]::::TokenOTP creado y validado exitosamente::::value::::" + token.getToken() + "::::");
		return token.getToken();
	}

	String crearNumeroRandom() {
		String numero = "";
		do {
			numero = numero + String.valueOf((int) (Math.random() * 999998 + 1)) + "";
		} while (numero.length() < 6);

		if (numero.length() > 6) {
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

		if (res.getCodigo() == Constantes.SUCCES && res.getListaEntity().isEmpty()) {
			token.setToken(numero);
			token.setEsValido(true);
			log.info("::::[verificarExistenciaToken]::::Token es valido);:::");
		}

		User user = null;
		if (res.getCodigo() == Constantes.SUCCES && !res.getListaEntity().isEmpty()) {
			for (TokenOTP objeto : res.getListaEntity()) {
				token = objeto;
				user = userDao.getOneById(token.getUsuario().getId()).getEntity();
				token.setUsuario(user);
			}
			log.info("::::[verificarExistenciaToken]::::Token existe);:::");
		}

		return token;
	}

	@Override
	public SuperGenericResponse validarToken(ValidarTokenOTPDto tokenOtp) throws SrvValidacionException {
		
		JwtRes res = new JwtRes();
		res.setCodigo(Constantes.ERROR);
		res.setMensaje(Constantes.FAIL);
		
		log.info(
				"::::[INICIO]::::[esTokenVencidoOrValidado]::::Incio de servicio para validar token y activar cuenta::::");

		log.info("::::[esTokenVencidoOrValidado]::::Datos recibidoss::::value::::"
				+ ObjectMapperUtils.ObjectToJsonString(tokenOtp));

		TokenOTP token = verificarExistenciaToken(tokenOtp.getToken());

		if (token == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error token no encontrado");

		if (esTokenVencidoOrValidado(token))
			throw new SrvValidacionException(Constantes.ERROR, "Error token invalido");

		if (validacionDeToken(token).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error interno en el proceso de validacion de token");

		if (activarCuenta(token.getUsuario()).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error interno en el proceso de activacion de cuenta");
		log.info("::::[esTokenVencidoOrValidado]::::se  activo la  cuenta del usuario::::value::::"
				+ token.getUsuario().getEmail() + "::::");
		
		String jwt = tokenManager.generarToken((token.getUsuario()));
		
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setJwt(jwt);
		log.info("::::[esTokenVencidoOrValidado]::::Fin del  servicio para validar y activar cuenta del usuario::::");

		return res;
	}

	public Boolean esTokenVencidoOrValidado(TokenOTP token) {
		log.info(
				"::::[INICIO]::::[esTokenVencidoOrValidado]::::Incio de metodo para verificar  si el token es valido y no esta vencido::::");

		if (token.getFechaDeVencimiento().before(new Date(System.currentTimeMillis())))
			return true;
		log.info("::::[esTokenVencidoOrValidado]::::Token No esta vencido::::");
		if (!token.getEsValido())
			return true;
		log.info("::::[esTokenVencidoOrValidado]::::Token es valido::::");
		if (token.getEstaVerificado())
			return true;
		log.info("::::[esTokenVencidoOrValidado]::::Token No ha sido validado::::");
		log.info("::::[FIN]::::[esTokenVencidoOrValidado]::::Fin de metodo::::");
		return false;
	}

	public SuperGenericResponse activarCuenta(User user) throws SrvValidacionException {
		user.setCuentaActiva(true);
		return userDao.update(user);
	}

	public SuperGenericResponse validacionDeToken(TokenOTP token) {
		token.setEstaVerificado(true);
		token.setEsValido(false);
		return srvTokenOTP.update(token);
	}

	@Override
	public void eliminarTokensObsoletos() {
		log.info("::::[PROCESO INTERNO]::::Proceso interno para eliminar los token obsoletos::::");
		srvTokenOTP = new DaoTokenOtpImpl();
		List<TokenOTP> ls = srvTokenOTP.getAll().getEntity();
		for (TokenOTP token : ls) {
			
			log.info("::::[PROCESO INTERNO]:::" + !token.getEsValido() +  "::::");
			log.info("::::[PROCESO INTERNO]:::" + token.getEstaVerificado() +  "::::");
			log.info("::::[PROCESO INTERNO]:::" + token.getFechaDeVencimiento().before(new Date(System.currentTimeMillis())) +  "::::");
			
			
			if(token.getFechaDeVencimiento().before(new Date(System.currentTimeMillis()))) {
				log.info("::::[PROCESO INTERNO:::Eliminando token::::value::::" + token.getId() + "::::");
				srvTokenOTP.delete(token);
			}
			
			if (!token.getEsValido() && token.getEstaVerificado()) {
				log.info("::::[PROCESO INTERNO:::Eliminando token::::value::::" + token.getId() + "::::");
				srvTokenOTP.delete(token);
			}
		}

	}
	
	
	
	

}
