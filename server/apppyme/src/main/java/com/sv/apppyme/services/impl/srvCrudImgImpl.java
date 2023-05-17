package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.ImgDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoImg;
import com.sv.apppyme.services.ICrudImg;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Log4jUtils;

@Service
public class srvCrudImgImpl implements ICrudImg {

Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IRepoImg ImgDao;
	
	/**
	 * Metodo para validar la data recibida del front
	 * @param imgInfo
	 * @param key
	 * @return una respuesta boolena con el status de todas las valdiaciones
	 */
	public boolean isDataValida(ImgDto imgInfo, String key) {
		
		if(imgInfo == null) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::informacion de Img general viene null!");
			return false;
		}
		
		if(imgInfo.getId() <= 0) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::Id no valido!");
			return false;
		}
		
		if(imgInfo.getBase64().trim().isBlank() || imgInfo.getBase64().trim().isEmpty()) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::Base64 de la Img a ingresar no es valido!");
			return false;
		}
		
		log.info("[" + key + "]" +  "::::[isDataValida]::::Data validada correctamente!");
		return true;
	}
	
	/**
	 * Metodo para crear objecto de tipo Img utilizando la informacion recibida por el cliente
	 * @param imgInfo
	 * @param key
	 * @return Img
	 */
	public Img crearObjectoImg(ImgDto imgInfo, String key) {
		Img aux = new  Img();
		try {
			aux.setId(imgInfo.getId());
			aux.setBase64(imgInfo.getBase64());
			return aux;
		} catch (Exception e) {
			log.info("[" + key + "]" +  "::::[crearObjectoImg]::::Error generico en la creacion de Objecto Img con data recibida!");
			log.info(Log4jUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public SuperGenericResponse insertImg(ImgDto imgInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Img Img = new Img();
		
		//Validando la data recibida
		imgInfo.setId(1);
		if(!isDataValida(imgInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		Img = crearObjectoImg(imgInfo, key);
		if(Img == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//Insertando en la base de datos!
		if(ImgDao.insert(Img).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de insertar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[insertImg]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}
	
	@Override
	public SuperGenericResponse updateImg(ImgDto imgInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Img Img = new Img();
		
		//Validando la data recibida
		if(!isDataValida(imgInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		Img = crearObjectoImg(imgInfo, key);
		if(Img == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//actualizando en la base de datos!
		if(ImgDao.update(Img).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de actualizar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[UpdateImg]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	@Override
	public SuperGenericResponse deleteImg(ImgDto imgInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Img Img = new Img();
		
		//Validando la data recibida
		imgInfo.setBase64("relleno para validacion");
		if(!isDataValida(imgInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		Img = crearObjectoImg(imgInfo, key);
		if(Img == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//elimninando en la base de datos!
		if(ImgDao.delete(Img).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de actualizar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[UpdateImg]::::Registro eliminado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}
	
	@Override
	public GenericEntityResponse<Img> getOneByIDImg(ImgDto imgInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<Img> res = new GenericEntityResponse<>(Constantes.ERROR, "Error generico en obtener por ID");
		Img Img = new Img();
		
		//Validando la data recibida
		imgInfo.setBase64("relleno para validacion");
		if(!isDataValida(imgInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//obteniendo en la base de datos!
		Img = ImgDao.getOneById(imgInfo.getId()).getEntity();
		if(Img == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[getOneByIDImg]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(Img);
		return res;
	}

	public GenericEntityResponse<List<Img>> getAllImg() throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<List<Img>> res = new GenericEntityResponse<>(Constantes.ERROR, "Error generico en obtener por ID");
		List<Img> Img = new ArrayList<>();
		
		//obteniendo en la base de datos!
		Img = ImgDao.getAll().getEntity();
		if(Img.isEmpty())
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener todos en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[getOneByIDImg]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setListaEntity(Img);
		return res;
	}

}
