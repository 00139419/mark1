package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.CategoriaDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoCategoria;
import com.sv.apppyme.services.ICrudCategoria;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Log4jUtils;

@Service
public class SrvCrudCategoriaImpl implements ICrudCategoria{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IRepoCategoria categoriaDao;
	
	/**
	 * Metodo para validar la data recibida del front
	 * @param categoriaInfo
	 * @param key
	 * @return una respuesta boolena con el status de todas las valdiaciones
	 */
	public boolean isDataValida(CategoriaDto categoriaInfo, String key) {
		
		if(categoriaInfo == null) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::informacion de categoria general viene null!");
			return false;
		}
		
		if(categoriaInfo.getId() <= 0) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::Id no valido!");
			return false;
		}
		
		if(categoriaInfo.getNombre().trim().isBlank() || categoriaInfo.getNombre().trim().isEmpty()) {
			log.info("[" + key + "]" +  "::::[isDataValida]::::nombre de la categoria a ingresar no es valido!");
			return false;
		}
		
		log.info("[" + key + "]" +  "::::[isDataValida]::::Data validada correctamente!");
		return true;
	}
	
	/**
	 * Metodo para crear objecto de tipo categoria utilizando la informacion recibida por el cliente
	 * @param categoriaInfo
	 * @param key
	 * @return Categoria
	 */
	public Categoria crearObjectoCategoria(CategoriaDto categoriaInfo, String key) {
		Categoria aux = new  Categoria();
		try {
			aux.setId(categoriaInfo.getId());
			aux.setNombre(categoriaInfo.getNombre());
			return aux;
		} catch (Exception e) {
			log.info("[" + key + "]" +  "::::[crearObjectoCategoria]::::Error generico en la creacion de Objecto categoria con data recibida!");
			log.info(Log4jUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public SuperGenericResponse insertCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Categoria categoria = new Categoria();
		
		//Validando la data recibida
		categoriaInfo.setId(1);
		if(!isDataValida(categoriaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		categoria = crearObjectoCategoria(categoriaInfo, key);
		if(categoria == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//Insertando en la base de datos!
		if(categoriaDao.insert(categoria).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de insertar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[insertCategoria]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}
	
	@Override
	public SuperGenericResponse updateCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Categoria categoria = new Categoria();
		
		//Validando la data recibida
		if(!isDataValida(categoriaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		categoria = crearObjectoCategoria(categoriaInfo, key);
		if(categoria == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//actualizando en la base de datos!
		if(categoriaDao.update(categoria).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de actualizar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[UpdateCategoria]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	@Override
	public SuperGenericResponse deleteCategoria(CategoriaDto categoriaInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Categoria categoria = new Categoria();
		
		//Validando la data recibida
		categoriaInfo.setNombre("relleno para validacion");
		if(!isDataValida(categoriaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//creando objecto que se insertara en l base de datos
		categoria = crearObjectoCategoria(categoriaInfo, key);
		if(categoria == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de crear objecto con la data recibida!");
		
		//elimninando en la base de datos!
		if(categoriaDao.delete(categoria).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de actualizar en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[UpdateCategoria]::::Registro eliminado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}
	
	@Override
	public GenericEntityResponse<Categoria> getOneByIDCategoria(CategoriaDto categoriaInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<Categoria> res = new GenericEntityResponse<>(Constantes.ERROR, "Error generico en obtener por ID");
		Categoria categoria = new Categoria();
		
		//Validando la data recibida
		categoriaInfo.setNombre("relleno para validacion");
		if(!isDataValida(categoriaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");
		
		//obteniendo en la base de datos!
		categoria = categoriaDao.getOneById(categoriaInfo.getId()).getEntity();
		if(categoria == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[getOneByIDCategoria]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(categoria);
		return res;
	}

	public GenericEntityResponse<List<Categoria>> getAllCategoria() throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<List<Categoria>> res = new GenericEntityResponse<>(Constantes.ERROR, "Error generico en obtener por ID");
		List<Categoria> categoria = new ArrayList<>();
		
		//obteniendo en la base de datos!
		categoria = categoriaDao.getAll().getEntity();
		if(categoria.isEmpty())
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener todos en la base de datos!");
		
		log.info("[" + key + "]" +  "::::[getOneByIDCategoria]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setListaEntity(categoria);
		return res;
	}

}
