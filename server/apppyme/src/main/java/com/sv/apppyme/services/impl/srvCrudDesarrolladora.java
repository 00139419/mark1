package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.DesarrolladoraDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoDesarrolladora;
import com.sv.apppyme.services.ICrudDesarrolladora;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Log4jUtils;

@Service
public class srvCrudDesarrolladora implements ICrudDesarrolladora {
	
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IRepoDesarrolladora DesarrolladoraDao;
	
	/**
	 * Metodo para validar la data recibida del front
	 * @param desarrolladoraInfo
	 * @param key
	 * @return una respuesta boolena con el status de todas las valdiaciones
	 */
	public boolean isDataValida(DesarrolladoraDto desarrolladoraInfo, String key) {
		
		if(desarrolladoraInfo == null) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::informacion de Desarrolladora general viene null!");
			return false;
		}
		
		if(desarrolladoraInfo.getId() <= 0) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::Id no valido!");
			return false;
		}
		
		if(desarrolladoraInfo.getNombre().trim().isBlank() || desarrolladoraInfo.getNombre().trim().isEmpty()) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::nombre de la Desarrolladora a ingresar no es valido!");
			return false;
		}
		
		log.info("[" + key + "]" +  "::::[isDataValida]::::Data validada correctamente!");
		return true;
	}
	
	/**
	 * Metodo para crear objecto de tipo Desarrolladora utilizando la informacion recibida por el cliente
	 * @param desarrolladoraInfo
	 * @param key
	 * @return Desarrolladora
	 */
	public Desarrolladora crearObjectoDesarrolladora(DesarrolladoraDto desarrolladoraInfo, String key) {
		Desarrolladora aux = new  Desarrolladora();
		try {
			aux.setId(desarrolladoraInfo.getId());
			aux.setNombre(desarrolladoraInfo.getNombre());
			return aux;
		} catch (Exception e) {
			log.info("[" + key + "]" +  "::::[crearObjectoDesarrolladora]::::Error generico en la creacion de Objecto Desarrolladora con data recibida!");
			log.info(Log4jUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public SuperGenericResponse insertDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Desarrolladora Desarrolladora = new Desarrolladora();
		
		//Validando la data recibida
		desarrolladoraInfo.setId(1);
		if(!isDataValida(desarrolladoraInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		Desarrolladora = crearObjectoDesarrolladora(desarrolladoraInfo, key);
		if(Desarrolladora == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//Insertando en la base de datos!
		if(DesarrolladoraDao.insert(Desarrolladora).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de insertar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[insertDesarrolladora]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}
	
	@Override
	public SuperGenericResponse updateDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Desarrolladora Desarrolladora = new Desarrolladora();
		
		//Validando la data recibida
		if(!isDataValida(desarrolladoraInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		Desarrolladora = crearObjectoDesarrolladora(desarrolladoraInfo, key);
		if(Desarrolladora == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//actualizando en la base de datos!
		if(DesarrolladoraDao.update(Desarrolladora).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de actualizar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[updateDesarrolladora]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	@Override
	public SuperGenericResponse deleteDesarrolladora(DesarrolladoraDto desarrolladoraInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Desarrolladora Desarrolladora = new Desarrolladora();
		
		//Validando la data recibida
		desarrolladoraInfo.setNombre("relleno para validacion");
		if(!isDataValida(desarrolladoraInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		Desarrolladora = crearObjectoDesarrolladora(desarrolladoraInfo, key);
		if(Desarrolladora == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//elimninando en la base de datos!
		if(DesarrolladoraDao.delete(Desarrolladora).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de actualizar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[UpdateDesarrolladora]::::Registro eliminado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}
	
	@Override
	public GenericEntityResponse<Desarrolladora> getOneByIDDesarrolladora(DesarrolladoraDto desarrolladoraInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<Desarrolladora> res = new GenericEntityResponse<>(Constantes.ERROR, "Error generico en obtener por ID");
		Desarrolladora Desarrolladora = new Desarrolladora();
		
		//Validando la data recibida
		desarrolladoraInfo.setNombre("relleno para validacion");
		if(!isDataValida(desarrolladoraInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//obteniendo en la base de datos!
		Desarrolladora = DesarrolladoraDao.getOneById(desarrolladoraInfo.getId()).getEntity();
		if(Desarrolladora == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[getOneByIDDesarrolladora]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(Desarrolladora);
		return res;
	}

	public GenericEntityResponse<List<Desarrolladora>> getAllDesarrolladora() throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<List<Desarrolladora>> res = new GenericEntityResponse<>(Constantes.ERROR, "Error generico en obtener por ID");
		List<Desarrolladora> Desarrolladora = new ArrayList<>();
		
		//obteniendo en la base de datos!
		Desarrolladora = DesarrolladoraDao.getAll().getEntity();
		if(Desarrolladora.isEmpty())
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener todos en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[getOneByIDDesarrolladora]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setListaEntity(Desarrolladora);
		return res;
	}
}
