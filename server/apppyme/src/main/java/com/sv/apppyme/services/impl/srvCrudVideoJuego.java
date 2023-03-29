package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoCategoria;
import com.sv.apppyme.repository.IRepoDesarrolladora;
import com.sv.apppyme.repository.IRepoImg;
import com.sv.apppyme.repository.IRepoPlataforma;
import com.sv.apppyme.repository.IRepoVideojuego;
import com.sv.apppyme.services.ICrudVideoJuego;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.ObjectMapperUtils;

@Service
public class srvCrudVideoJuego implements ICrudVideoJuego {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	IRepoImg ImgDao;

	@Autowired
	IRepoVideojuego videojuegoDao;
	
	@Autowired
	IRepoCategoria categoriaDao;
	
	@Autowired
	IRepoPlataforma plataformaDao;
	
	@Autowired
	IRepoImg imgDao;

	@Autowired
	IRepoDesarrolladora desarrolladoraDao;

	public void mostrarDataRecibida(Object object, String key) {
		log.info("[" + key + "]" + ":::: Imprimieno data recibida::::" + ObjectMapperUtils.ObjectToJsonString(object) + "::::");
	}

	@Override
	public SuperGenericResponse insertVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();

		mostrarDataRecibida(videojuegoInfo, key);

		// Insertando la imagen en la base de datos
		if (!insetarImgDelJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de guarda Img");

		// insertando el videojuego en la base de datos
		if (!insetarJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de guarda videojuego");

		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	public boolean insetarImgDelJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Img img = new Img();
		img.setBase64(videojuegoInfo.getImgbase64());

		if (ImgDao.insert(img).getCodigo() != Constantes.SUCCES)
			return false;
		log.info("[" + key + "]"
				+ "::::[insetarImgDelJuegoSucces]::: Imagen insertada en base de datos correctamente:::");
		return true;
	}

	public boolean insetarJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Img img = ImgDao.getOneByBase64(videojuegoInfo.getImgbase64()).getEntity();
		Videojuego vjE = new Videojuego();
		vjE.setNombre(videojuegoInfo.getNombre());
		vjE.setCategoria(new Categoria(videojuegoInfo.getCategoria()));
		vjE.setPrecio(videojuegoInfo.getPrecio());
		vjE.setFechaDeLanzamiento(DateUtils.convetirStringToDate(videojuegoInfo.getFechaDeLanzamiento()));
		vjE.setDesarrolladora(new Desarrolladora(videojuegoInfo.getDesarrolladora()));
		vjE.setImg(img);
		vjE.setCantidadDisponible(videojuegoInfo.getCantidadDisponible());
		vjE.setDescripcion(videojuegoInfo.getDescripcion());
		vjE.setPlataforma(new Plataforma(videojuegoInfo.getPlataforma()));

		if (videojuegoDao.insert(vjE).getCodigo() != Constantes.SUCCES)
			return false;

		log.info("[" + key + "]"
				+ "::::[insetarImgDelJuegoSucces]::: videojuego insertada en base de datos correctamente:::");
		return true;
	}

	@Override
	public SuperGenericResponse updateVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();

		mostrarDataRecibida(videojuegoInfo, key);

		// actualizando la imagen en la base de datos
		if (!updateImgDelJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de actualizar Img");

		// actualizando el videojuego en la base de datos
		if (!updateJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de actualizar videojuego");

		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	public boolean updateImgDelJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Img img = new Img();
		img.setId(videojuegoInfo.getImgId());
		img.setBase64(videojuegoInfo.getImgbase64());

		if (ImgDao.update(img).getCodigo() != Constantes.SUCCES)
			return false;
		log.info("[" + key + "]"
				+ "::::[updateImgDelJuegoSucces]::: Imagen actualizado en base de datos correctamente:::");
		return true;
	}

	public boolean updateJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Img img = ImgDao.getOneByBase64(videojuegoInfo.getImgbase64()).getEntity();
		Videojuego vjE = new Videojuego();
		vjE.setId(videojuegoInfo.getId());
		vjE.setNombre(videojuegoInfo.getNombre());
		vjE.setCategoria(new Categoria(videojuegoInfo.getCategoria()));
		vjE.setPrecio(videojuegoInfo.getPrecio());
		vjE.setFechaDeLanzamiento(DateUtils.convetirStringToDate(videojuegoInfo.getFechaDeLanzamiento()));
		vjE.setDesarrolladora(new Desarrolladora(videojuegoInfo.getDesarrolladora()));
		vjE.setImg(img);
		vjE.setCantidadDisponible(videojuegoInfo.getCantidadDisponible());
		vjE.setDescripcion(videojuegoInfo.getDescripcion());
		vjE.setPlataforma(new Plataforma(videojuegoInfo.getPlataforma()));

		if (videojuegoDao.update(vjE).getCodigo() != Constantes.SUCCES)
			return false;

		log.info("[" + key + "]"
				+ "::::[updateJuegoSucces]::: videojuego actualizado en base de datos correctamente:::");
		return true;
	}

	@Override
	public SuperGenericResponse deleteVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();

		mostrarDataRecibida(videojuegoInfo, key);

		// eliminando la imagen en la base de datos
		if (!deleteImgDelJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de eliminar Img");

		// eliminando el videojuego en la base de datos
		if (!deleteJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de eliminar videojuego");

		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	public boolean deleteImgDelJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Img img = new Img();
		img.setId(videojuegoInfo.getImgId());

		if (ImgDao.delete(img).getCodigo() != Constantes.SUCCES)
			return false;
		log.info("[" + key + "]"
				+ "::::[deleteImgDelJuegoSucces]::: Imagen eliminada en base de datos correctamente:::");
		return true;
	}

	public boolean deleteJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Videojuego vjE = new Videojuego();
		vjE.setId(videojuegoInfo.getId());

		if (videojuegoDao.delete(vjE).getCodigo() != Constantes.SUCCES)
			return false;

		log.info("[" + key + "]" + "::::[deleteJuegoSucces]::: videojuego eliminado en base de datos correctamente:::");
		return true;
	}

	@Override
	public GenericEntityResponse<Videojuego> getOneByIDVideojuego(VideoJuegoDto videojuegoInfo)
			throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();

		mostrarDataRecibida(videojuegoInfo, key);
		
		GenericEntityResponse<Videojuego> res = new GenericEntityResponse<>();
		Videojuego v = new Videojuego();

		// obteniendo la da del la base de datps por ID
		GenericEntityResponse<Videojuego> resDao = videojuegoDao.getOneById(videojuegoInfo.getId());

		if (resDao.getCodigo() != Constantes.SUCCES) {
			throw new SrvValidacionException(Constantes.ERROR,
					"Error en el proceso de obtener el videojuego con id " + videojuegoInfo.getId());
		}

		v = setearAtributosVideoJuegosIndividuales(resDao.getEntity(), key);

		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(v);
		return res;
	}

	public Videojuego setearAtributosVideoJuegosIndividuales(Videojuego v, String key) throws SrvValidacionException {

		GenericEntityResponse<Categoria> resCat = categoriaDao.getOneById(v.getCategoria().getId());
		if (resCat.getCodigo() != Constantes.SUCCES) {
			log.info("[" + key + "]" + "::::[setearAtributosVideoJuegosIndividuales]:::[ERROR]::::Error en el proceso de obtener la categoria id " + v.getCategoria().getId() + "::::");
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de obtener la categoria " + v.getCategoria().getId());
		}

		GenericEntityResponse<Plataforma> respla = plataformaDao.getOneById(v.getPlataforma().getId());
		if (respla.getCodigo() != Constantes.SUCCES){
			log.info("[" + key + "]" + "::::[setearAtributosVideoJuegosIndividuales]:::[ERROR]::::Error en el proceso de obtener la categoria id " + v.getPlataforma().getId() + "::::");
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de obtener la plataforma " +  + v.getPlataforma().getId());
		}

		GenericEntityResponse<Img> resImg = imgDao.getOneById(v.getImg().getId());
		if (resImg.getCodigo() != Constantes.SUCCES){
			log.info("[" + key + "]" + "::::[setearAtributosVideoJuegosIndividuales]:::[ERROR]::::Error en el proceso de obtener la categoria id " + v.getImg().getId() + "::::");
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de obtener la img " +v.getImg().getId());
		}

		GenericEntityResponse<Desarrolladora> resDesa = desarrolladoraDao.getOneById(v.getDesarrolladora().getId());
		if (resDesa.getCodigo() != Constantes.SUCCES){
			log.info("[" + key + "]" + "::::[setearAtributosVideoJuegosIndividuales]:::[ERROR]::::Error en el proceso de obtener la categoria id " + v.getDesarrolladora().getId() + "::::");
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de obtener la desarrolladora " + v.getDesarrolladora().getId());
		}

		v.setCategoria(resCat.getEntity());
		v.setPlataforma(respla.getEntity());
		v.setImg(resImg.getEntity());
		v.setDesarrolladora(resDesa.getEntity());

		log.info("[" + key + "]" + ":::::[setearAtributosfetOneById]::::Atributos setteados correctamente::::");
		return v;
	}

	@Override
	public GenericEntityResponse<List<Videojuego>> getAllVideojuego() throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// obteniendo la da del la base de datps por ID
		GenericEntityResponse<List<Videojuego>> res = new GenericEntityResponse<>();
		GenericEntityResponse<List<Videojuego>> resDao = videojuegoDao.getAll();

		List<Videojuego> ls = new ArrayList<>();

		if (resDao.getCodigo() != Constantes.SUCCES) {
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de obtener todos los videojuegos");
		}

		// TODO setear las entidades
		for (Videojuego v : resDao.getEntity()) {
			ls.add(setearAtributosVideoJuegosIndividuales(v, key));
		}

		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(ls);

		return res;
	}

}
