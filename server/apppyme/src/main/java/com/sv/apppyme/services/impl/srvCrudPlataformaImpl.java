package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.PlataformaDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoPlataforma;
import com.sv.apppyme.services.ICrudPlataforma;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Log4jUtils;

@Service
public class srvCrudPlataformaImpl implements ICrudPlataforma {
	Logger log = Logger.getLogger(getClass());

	@Autowired
	IRepoPlataforma plataformaDao;

	/**
	 * Metodo para validar la data recibida del front
	 * 
	 * @param plataformaInfo
	 * @param key
	 * @return una respuesta boolena con el status de todas las valdiaciones
	 */
	public boolean isDataValida(PlataformaDto plataformaInfo, String key) {

		if (plataformaInfo == null) {
			log.info("[" + key + "]" + "::::[isDataValida]::::informacion de Plataforma general viene null!");
			return false;
		}

		if (plataformaInfo.getId() <= 0) {
			log.info("[" + key + "]" + "::::[isDataValida]::::Id no valido!");
			return false;
		}

		if (plataformaInfo.getNombre().trim().isBlank() || plataformaInfo.getNombre().trim().isEmpty()) {
			log.info("[" + key + "]" + "::::[isDataValida]::::nombre de la Plataforma a ingresar no es valido!");
			return false;
		}

		log.info("[" + key + "]" + "::::[isDataValida]::::Data validada correctamente!");
		return true;
	}

	/**
	 * Metodo para crear objecto de tipo Plataforma utilizando la informacion
	 * recibida por el cliente
	 * 
	 * @param plataformaInfo
	 * @param key
	 * @return Plataforma
	 */
	public Plataforma crearObjectoPlataforma(PlataformaDto plataformaInfo, String key) {
		Plataforma aux = new Plataforma();
		try {
			aux.setId(plataformaInfo.getId());
			aux.setNombre(plataformaInfo.getNombre());
			return aux;
		} catch (Exception e) {
			log.info("[" + key + "]"
					+ "::::[crearObjectoPlataforma]::::Error generico en la creacion de Objecto Plataforma con data recibida!");
			log.info(Log4jUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public SuperGenericResponse insertPlataforma(PlataformaDto plataformaInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Plataforma plataforma = new Plataforma();

		// Validando la data recibida
		plataformaInfo.setId(1);
		if (!isDataValida(plataformaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");

		// creando objecto que se insertara en l base de datos
		plataforma = crearObjectoPlataforma(plataformaInfo, key);
		if (plataforma == null)
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceos de crear objecto con la data recibida!");

		// Insertando en la base de datos!
		if (plataformaDao.insert(plataforma).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de insertar en la base de datos!");

		log.info("[" + key + "]" + "::::[insertPlataforma]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	@Override
	public SuperGenericResponse updatePlataforma(PlataformaDto plataformaInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Plataforma plataforma = new Plataforma();

		// Validando la data recibida
		if (!isDataValida(plataformaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");

		// creando objecto que se insertara en l base de datos
		plataforma = crearObjectoPlataforma(plataformaInfo, key);
		if (plataforma == null)
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceos de crear objecto con la data recibida!");

		// actualizando en la base de datos!
		if (plataformaDao.update(plataforma).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceos de actualizar en la base de datos!");

		log.info("[" + key + "]" + "::::[updatePlataforma]::::Registro agregado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	@Override
	public SuperGenericResponse deletePlataforma(PlataformaDto plataformaInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, "Error generico en el servicio!");
		Plataforma plataforma = new Plataforma();

		// Validando la data recibida
		plataformaInfo.setNombre("relleno para validacion");
		if (!isDataValida(plataformaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");

		// creando objecto que se insertara en l base de datos
		plataforma = crearObjectoPlataforma(plataformaInfo, key);
		if (plataforma == null)
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceos de crear objecto con la data recibida!");

		// elimninando en la base de datos!
		if (plataformaDao.delete(plataforma).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceos de actualizar en la base de datos!");

		log.info("[" + key + "]" + "::::[UpdatePlataforma]::::Registro eliminado correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		return res;
	}

	@Override
	public GenericEntityResponse<Plataforma> getOneByIDPlataforma(PlataformaDto plataformaInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<Plataforma> res = new GenericEntityResponse<>(Constantes.ERROR,
				"Error generico en obtener por ID");
		Plataforma plataforma = new Plataforma();

		// Validando la data recibida
		plataformaInfo.setNombre("relleno para validacion");
		if (!isDataValida(plataformaInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Data no es valida!");

		// obteniendo en la base de datos!
		plataforma = plataformaDao.getOneById(plataformaInfo.getId()).getEntity();
		if (plataforma == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceos de obtener en la base de datos!");

		log.info("[" + key + "]" + "::::[getOneByIDPlataforma]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(plataforma);
		return res;
	}

	public GenericEntityResponse<List<Plataforma>> getAllPlataforma() throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		GenericEntityResponse<List<Plataforma>> res = new GenericEntityResponse<>(Constantes.ERROR,
				"Error generico en obtener por ID");
		List<Plataforma> plataforma = new ArrayList<>();

		// obteniendo en la base de datos!
		plataforma = plataformaDao.getAll().getEntity();
		if (plataforma.isEmpty())
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceos de obtener todos en la base de datos!");

		log.info("[" + key + "]" + "::::[getOneByIDPlataforma]::::Registro obtenido correctamente!");
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setListaEntity(plataforma);
		return res;
	}
}
