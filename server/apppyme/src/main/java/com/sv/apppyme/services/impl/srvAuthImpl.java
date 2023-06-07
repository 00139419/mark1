package com.sv.apppyme.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.apppyme.controllers.dto.TokenDto;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IAuth;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Mensajeria;
import com.sv.apppyme.utils.TokenManager;
import com.sv.apppyme.utils.encriptacion.MD5;

@Service
public class srvAuthImpl implements IAuth {

	@Autowired
	ObjectMapper mapper;

	Logger log = Logger.getLogger(getClass());

	@Autowired
	ISignUp srvDataimpl;

	@Autowired
	TokenManager tokenManager;

	@Override
	public GenericEntityResponse<TokenDto> login(UsuarioDto userinfo)
			throws SrvValidacionException, JsonProcessingException {
		GenericEntityResponse<TokenDto> resServicio = new GenericEntityResponse<>();
		log.info("::::[INICIO]::::[Login]::::Incicio servicio login::::");
		log.info(":::Login]::::Inicio mostrando datos recibidos en Json::::");
		log.info(mapper.writeValueAsString(userinfo));
		log.info(":::Login]::::Fin mostrando datos recibidos en Json::::");
		log.info("::::[Login]::::Obteniendo usario::::");
		GenericEntityResponse<User> resDaoUsario = srvDataimpl.obtenerUsuarioByUsername(userinfo);
		log.info("::::[login]:::Respuesta obtenida del DAO::::");
		log.info("::::[login]:::codigo::::" + resDaoUsario.getCodigo() + "::::");
		log.info("::::[login]:::mensaje::::" + resDaoUsario.getMensaje() + "::::");
		log.info("::::[login]:::entity::::" + resDaoUsario.getEntity() + "::::");

		if (resDaoUsario.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_LOGIN_ERROR);

		log.info(":::Login]::::Usuario obtenido correctamente::::");
		log.info(":::Login]::::Verificando contraseña::::");
		String encriptedPassword = "";
		try {
			encriptedPassword = MD5.encriptar(userinfo.getPassword());
		} catch (Exception e) {
			throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_ENCRIPTAR_TEXTO);
		}
		SuperGenericResponse resEncriptacion = MD5.comparePasswords(encriptedPassword,
				resDaoUsario.getEntity().getPassword());

		if (resEncriptacion.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_CONTRASEÑA_INCORRECTA);

		log.info(":::Login]::::Contraseña verificada correctamente!::::");
		log.info(":::Login]::::Llamando al servicio de crear tokenJWT!::::");
		String jwt = tokenManager.generarToken(resDaoUsario.getEntity());

		if (jwt == null || jwt.isBlank() || jwt.isEmpty())
			throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_CREANDO_JWT);

		resServicio.setCodigo(Constantes.SUCCES);
		resServicio.setMensaje(Constantes.OK);
		resServicio.setEntity(new TokenDto(jwt));
		log.info(":::Login]::::Fin de servicio crear tokenJWT!::::");
		log.info(":::[FIN]::::Login]::::retornando respuesta del servicio::::");

		return resServicio;

	}

}
