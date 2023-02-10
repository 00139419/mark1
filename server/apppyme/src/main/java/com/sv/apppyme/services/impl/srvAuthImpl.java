package com.sv.apppyme.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IAuth;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Encriptacion;
import com.sv.apppyme.utils.Mensajeria;

@Service
public class srvAuthImpl implements IAuth {
	
	@Autowired
	ObjectMapper mapper;
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IData srvDataimpl;

	@Override
	public SuperGenericResponse login(UsuarioDto userinfo) throws SrvValidacionException {
		log.info("::::[INICIO]::::[Login]::::Incicio servicio login::::");
			try {
				log.info(":::Login]::::Inicio mostrando datos recibidos en Json::::");
				log.info(mapper.writeValueAsString(userinfo));
				log.info(":::Login]::::Fin mostrando datos recibidos en Json::::");
			} catch (Exception e) {
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_PARSEAR_OBJECT_TO_STRING);
			}
			log.info("::::[Login]::::Obteniendo usario::::");
			GenericEntityResponse<Usuario> resDaoUsario = srvDataimpl.obtenerUsuarioByUsername(userinfo);
			log.info("::::[getAllRoles]:::Respuesta obtenida del DAO::::");
			log.info("::::[getAllRoles]:::codigo::::" + resDaoUsario.getCodigo() + "::::");
			log.info("::::[getAllRoles]:::mensaje::::" + resDaoUsario.getMensaje() + "::::");
			log.info("::::[getAllRoles]:::entity::::" + resDaoUsario.getEntity() + "::::");
			
			if(resDaoUsario.getCodigo() != Constantes.SUCCES) 
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_LOGIN_ERROR);
			
			log.info(":::Login]::::Usuario obtenido correctamente::::");
			log.info(":::Login]::::Verificando contraseña::::");
			String encriptedPassword = "";
			try {
				encriptedPassword = Encriptacion.encriptar(userinfo.getPassword());
			} catch (Exception e) {
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_ENCRIPTAR_TEXTO);
			}
			SuperGenericResponse resEncriptacion = Encriptacion.comparePasswords(encriptedPassword, resDaoUsario.getEntity().getPassword());
			
			if(resEncriptacion.getCodigo() != Constantes.SUCCES) 
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_CONTRASEÑA_INCORRECTA);
			log.info(":::Login]::::Contraseña verificada correctamente!::::");
			log.info(":::[FIN]::::Login]::::retornando respuesta del servicio::::");
			return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);

	}
	
	
}
