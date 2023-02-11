package com.sv.apppyme.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dao.IRolDao;
import com.sv.apppyme.dao.IUsuarioDao;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Encriptacion;
import com.sv.apppyme.utils.Mensajeria;

@Service
public class srvDataImpl implements IData {

	@Autowired
	IRolDao srvRolImpl;

	@Autowired
	IUsuarioDao srvUsuarioImpl;
	
	@Autowired
	ObjectMapper mapper;

	Logger log = Logger.getLogger(srvDataImpl.class);

	@Override
	public GenericEntityResponse<List<Rol>> getAllRoles() throws SrvValidacionException {
		log.info("::::[INICIO]::::[getAllRoles]:::Inicinado implementacion del servicio para obtener los roles::::");
		GenericEntityResponse<List<Rol>> resDao = srvRolImpl.getAll();
		log.info("::::[getAllRoles]:::Respuesta obtenida del DAO::::");
		log.info("::::[getAllRoles]:::codigo::::" + resDao.getCodigo() + "::::");
		log.info("::::[getAllRoles]:::mensaje::::" + resDao.getMensaje() + "::::");
		log.info("::::[getAllRoles]:::entity::::" + resDao.getEntity() + "::::");
		log.info("::::[getAllRoles]:::Lista<E>::::" + resDao.getListaEntity() + "::::");
		if (resDao.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, resDao.getMensaje());
		log.info("::::[getAllRoles]:::Retornando respuesta::::");
		log.info("::::[FIN]::::[getAllRoles]:::Fin implementacion del servicio para obtener los roles::::");
		
		return resDao;
	}
	
	@Override
	public SuperGenericResponse insertarUsuario(UsuarioDto userInfo) throws SrvValidacionException {
		SuperGenericResponse resServicio = new SuperGenericResponse();
			log.info("::::[INICIO]::::[insertarUsuario]:::Iniciando proceso de insertar nuevo usuario::::");
			GenericEntityResponse<Rol> resObtenerRol = null;
			Usuario usuario = new Usuario();
			log.info("::::[insertarUsuario]:::Inciando servicio de insertar usuario!::::");
			try {
				log.info(":::Login]::::Inicio mostrando datos recibidos en Json::::");
				log.info(mapper.writeValueAsString(userInfo));
				log.info(":::Login]::::Fin mostrando datos recibidos en Json::::");
			} catch (Exception e) {
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_PARSEAR_OBJECT_TO_STRING);
			}
			if( userInfo.getPassword() == null ||userInfo.getPassword().isBlank() || userInfo.getPassword().isEmpty())
				throw new SrvValidacionException(Constantes.ERROR, "Campo contraseña viene vacio!");
			
			log.info("::::[insertarUsuario]:::Inicio proceso de encriptar contraseña::::");
			String encryptedPassword = "";
			try {
				encryptedPassword = Encriptacion.encriptar(userInfo.getPassword());
			} catch (Exception e) {
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_ENCRIPTAR_TEXTO);
			}
			userInfo.setPassword(encryptedPassword);
			log.info("::::[insertarUsuario]:::Fin proceso de encriptar contraseña::::ContraseñaEncriptada:::" + encryptedPassword + "::::");
			log.info("::::[insertarUsuario]:::llamando servicio para obtener los roles::::");
			
			if (userInfo.getRol() != null)
				resObtenerRol = srvRolImpl.getRolByDescripcition(userInfo.getRol());
			
			if (userInfo.getRol() == null)
				resObtenerRol = srvRolImpl.getRolByDescripcition(Constantes.ROL_USER);
			
			log.info("::::[insertarUsuario]:::Respuesta obtenida del DAO::::");
			log.info("::::[insertarUsuario]:::codigo::::" + resObtenerRol.getCodigo() + "::::");
			log.info("::::[insertarUsuario]:::mensaje::::" + resObtenerRol.getMensaje() + "::::");
			log.info("::::[insertarUsuario]:::entity::::" + resObtenerRol.getEntity() + "::::");
			
			if (resObtenerRol == null || resObtenerRol.getCodigo() != Constantes.SUCCES) {
				log.info("::::[ERROR]::::[insertarUsuario]::::Error onteniendo el rol::::" + userInfo.getRol() + "::::");
				throw new SrvValidacionException(Constantes.ERROR, "Error obteniendo rol" + userInfo.getRol());
			}
			
			log.info("::::[insertarUsuario]:::Creando objeto Usuario::::");
			usuario.setUsername(userInfo.getUsername());
			usuario.setPassword(userInfo.getPassword());
			usuario.setRol(resObtenerRol.getEntity());
			log.info("::::[insertarUsuario]:::Usuario creado::::" + usuario.toString() + "::::");
			log.info("::::[insertarUsuario]:::Llamando al DAO para insertar usuario:::");
			resServicio = srvUsuarioImpl.insertar(usuario);
			
			if (resServicio.getCodigo() != Constantes.SUCCES) {
				log.info("::::[ERROR]::::[insertarUsuario]::::Error insertando el usuario::::" + usuario.toString() + "::::");
				throw new SrvValidacionException(Constantes.ERROR, "Error insertando usuario: " + usuario.toString());
			}
			
			log.info("::::[insertarUsuario]::::Usuario insertado correctamente::::usuario::::" + usuario.toString() + "::::");
			log.info("::::FIN[]::::[insertarUsuario]::::retornando repsuesta del implementacion del servicio::::");
			resServicio.setCodigo(Constantes.SUCCES);
			resServicio.setMensaje(Constantes.OK);
		
			return resServicio;
	}

	@Override
	public GenericEntityResponse<Usuario> obtenerUsuarioByUsername(UsuarioDto userInfo) throws SrvValidacionException {
		GenericEntityResponse<Usuario> resServicio = new GenericEntityResponse<>();
			log.info("::::[INICIO]::::[obtenerUsuarioByUsername]:::Inicinado implementacion del servicio para obtener usuario::::");
			log.info("::::[obtenerUsuarioByUsername]:::Llamando al DAO para obetner usuario:::");
			resServicio = srvUsuarioImpl.selectByUsername(userInfo.getUsername());
			log.info("::::[obtenerUsuarioByUsername]:::Respuesta obtenida del DAO::::");
			log.info("::::[obtenerUsuarioByUsername]:::codigo::::" + resServicio.getCodigo() + "::::");
			log.info("::::[obtenerUsuarioByUsername]:::mensaje::::" + resServicio.getMensaje() + "::::");
			log.info("::::[obtenerUsuarioByUsername]:::entity::::" + resServicio.getEntity() + "::::");
			
			if (resServicio.getCodigo() != Constantes.SUCCES)
				throw new SrvValidacionException(Constantes.ERROR, resServicio.getMensaje());
			
			log.info("::::[obtenerUsuarioByUsername]:::Llamando al DAO para ontener el rol:::");
			GenericEntityResponse<Rol> rol = srvRolImpl.getRolById(resServicio.getEntity().getRol().getId());
			log.info("::::[obtenerUsuarioByUsername]:::Respuesta obtenida del DAO::::");
			log.info("::::[obtenerUsuarioByUsername]:::codigo::::" + rol.getCodigo() + "::::");
			log.info("::::[obtenerUsuarioByUsername]:::mensaje::::" + rol.getMensaje() + "::::");
			log.info("::::[obtenerUsuarioByUsername]:::entity::::" + rol.getEntity() + "::::");
			
			if(rol.getCodigo() != Constantes.SUCCES)
				throw new SrvValidacionException(Constantes.ERROR, rol.getMensaje());
			
			log.info("::::[obtenerUsuarioByUsername]:::Seteando rol al usuario:::");
			Usuario usuarioFinal = resServicio.getEntity();
			usuarioFinal.setRol(rol.getEntity());
			resServicio.setEntity(usuarioFinal);
			log.info("::::[FIN]::::[obtenerUsuarioByUsername]::::retornando repsuesta del implementacion del servicio::::");
			resServicio.setCodigo(Constantes.SUCCES);
			resServicio.setMensaje(Constantes.OK);
			
			return resServicio;
	}

}
