package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.apppyme.controllers.dto.UsuarioDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.reports.dto.FormularioVinculacionDto;
import com.sv.apppyme.reports.repository.IReportManagerJasper;
import com.sv.apppyme.reports.repository.impl.srvReportManagerJasperimpl;
import com.sv.apppyme.reports.utils.DocumentTypesToGenerated;
import com.sv.apppyme.repository.IRepoRol;
import com.sv.apppyme.repository.IRepoUsuario;
import com.sv.apppyme.repository.impl.UsuarioDao;
import com.sv.apppyme.services.ISignUp;
import com.sv.apppyme.services.ITokenOTP;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Mensajeria;

@Service
public class srvSignUpImpl implements ISignUp {
	
	Logger log = Logger.getLogger(srvSignUpImpl.class);

	@Autowired
	IRepoRol rolDao;
	
	@Autowired
	IRepoUsuario userDao;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	IReportManagerJasper reportManagerJasper;
	
	@Autowired
	ITokenOTP srvTokenOTP;
	
	PasswordEncoder encoder;
	
	@Override
	public SuperGenericResponse registrarUsuario(UsuarioDto userInfo) throws SrvValidacionException {
		SuperGenericResponse resServicio = new SuperGenericResponse();
		FormularioVinculacionDto vinculacionDto = null;
			
			//mostrando data recibida del request
			log.info("::::[INICIO]::::[insertarUsuario]:::Iniciando proceso de insertar nuevo usuario::::");
			GenericEntityResponse<Rol> resObtenerRol = null;
			Usuario usuario = new Usuario();
			
			//verifiando que no exista ese username en la base de datos
			Usuario userregistered = obtenerUsuarioByUsername(userInfo).getEntity();
			
			if(userregistered != null) {
				log.info("::::[insertarUsuario]:::: Usuario ya esta registrado::::");
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_USUARIO_YA_REGISTRADO);
			}
			
			//encriptando contraseña
			if( userInfo.getPassword() == null ||userInfo.getPassword().isBlank() || userInfo.getPassword().isEmpty())
				throw new SrvValidacionException(Constantes.ERROR, "Campo contraseña viene vacio!");
			
			String encryptedPassword = "";
			try {
				 encoder = new BCryptPasswordEncoder();
				encryptedPassword = encoder.encode(userInfo.getPassword());
				userInfo.setPassword(encryptedPassword);
				log.info("::::[insertarUsuario]:::contraseña encriptada exitosamente::::value::::" + encryptedPassword + "::::");
			} catch (Exception e) {
				log.info("::::[insertarUsuario]:::Error al encriptar la contraseña::::value::::" + userInfo.getPassword() + "::::");
				throw new SrvValidacionException(Constantes.ERROR, Mensajeria.MJS_ERROR_ENCRIPTAR_TEXTO);
			}
			
			//obteniendo el codigo del rol del usuario
			if (userInfo.getRol() != null)
				resObtenerRol = rolDao.getOneByDescripcition(userInfo.getRol());
			
			if (userInfo.getRol() == null)
				resObtenerRol = rolDao.getOneByDescripcition(Constantes.ROL_USER);
			
			if (resObtenerRol == null || resObtenerRol.getCodigo() != Constantes.SUCCES) {
				log.info("::::[ERROR]::::[insertarUsuario]::::Error onteniendo el rol::::" + userInfo.getRol() + "::::");
				throw new SrvValidacionException(Constantes.ERROR, "Error obteniendo rol" + userInfo.getRol());
			}
			
			//Creando el Usuario
			usuario.setUsername(userInfo.getUsername());
			usuario.setPassword(userInfo.getPassword());
			usuario.setRol(resObtenerRol.getEntity());
			log.info("::::[insertarUsuario]:::entidad usuario creado correctamente::::" + usuario.toString() + "::::");
			
			//insertando al usuario en la base de datos, a traves del DAO
			if (userDao.insert(usuario).getCodigo() != Constantes.SUCCES) {
				log.info("::::[ERROR]::::[insertarUsuario]::::Error insertando el usuario::::" + usuario.toString() + "::::");
				throw new SrvValidacionException(Constantes.ERROR, "Error insertando usuario: " + usuario.toString());
			}
			
			log.info("::::[insertarUsuario]::::Usuario insertado correctamente::::usuario::::" + usuario.toString() + "::::");
			resServicio.setCodigo(Constantes.SUCCES);
			resServicio.setMensaje(Constantes.OK);
			
			//Creando el formulario de vinculacion
			Usuario userAux = obtenerUsuarioByUsername(userInfo).getEntity();
			vinculacionDto = new FormularioVinculacionDto();
			vinculacionDto.setId(userAux.getId());
			vinculacionDto.setNombres(userAux.getUsername());
			vinculacionDto.setPassword(userAux.getPassword());
			vinculacionDto.setRol(userAux.getRol().getDescripcion());
			vinculacionDto.setDateVinculation((new Date()).toString());
			List<Object> ls = new ArrayList<>();
			ls.add(vinculacionDto);
			
			String pdf = reportManagerJasper.generatedBase64JasperReport(ls, DocumentTypesToGenerated.FV);
			
			if(pdf.equals("")) {
				log.info("::::[insertarUsuario]::::Error generando el reporte:::");
				throw new SrvValidacionException(Constantes.ERROR, "Error creando el formulario de vinculacion");
			}
				
			log.info("::::[insertarUsuario]::::Reporte generado exitosamente:::");
			log.info(pdf);
			
			return resServicio;
	}

	@Override
	public GenericEntityResponse<Usuario> obtenerUsuarioByUsername(UsuarioDto userInfo) throws SrvValidacionException {
		GenericEntityResponse<Usuario> resServicio = new GenericEntityResponse<>();
			resServicio = userDao.getOneByUsername(userInfo.getUsername());
			
			if (resServicio.getCodigo() != Constantes.SUCCES)
				throw new SrvValidacionException(Constantes.ERROR, resServicio.getMensaje());
			
			if(resServicio.getEntity() != null) {
				GenericEntityResponse<Rol> rol = rolDao.getOneById(resServicio.getEntity().getRol().getId());
				
				if(rol.getCodigo() != Constantes.SUCCES)
					throw new SrvValidacionException(Constantes.ERROR, rol.getMensaje());
				
				Usuario usuarioFinal = resServicio.getEntity();
				usuarioFinal.setRol(rol.getEntity());
				resServicio.setEntity(usuarioFinal);
				resServicio.setCodigo(Constantes.SUCCES);
				resServicio.setMensaje(Constantes.OK);
			}
			
			
			return resServicio;
	}

	@Override
	public SuperGenericResponse esCuentaActiva(String username) throws SrvValidacionException {
		log.info("::::[INICIO]::::[obtenerUsuarioByUsername]:::Iniciando servicio para verificar que la cuenta del usuario este activa::::");
		
		userDao = new UsuarioDao();
		Usuario user = userDao.getOneByUsername(username).getEntity();
		log.info("::::[obtenerUsuarioByUsername]:::Usuario encontrado::::value::::" + user.toString()  + "::::");
		
		if(user.getCuentaActiva()) {
			log.info("::::[obtenerUsuarioByUsername]:::Cuenta ACTIVA::::");
			return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
		}else {
			log.info("::::[obtenerUsuarioByUsername]:::Cuenta NO ACTIVA::::");
			return new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);
		}
	}

}
