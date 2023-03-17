package com.sv.apppyme.services.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.entities.Reporte;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoCategoria;
import com.sv.apppyme.repository.IRepoReporte;
import com.sv.apppyme.repository.IRepoVideojuego;
import com.sv.apppyme.services.ITestTablas;
import com.sv.apppyme.utils.Constantes;

@Service
public class srvTestTablasImpl implements ITestTablas {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	IRepoCategoria categoriaDao;

	@Autowired
	IRepoVideojuego videoJuegoDao;

	@Autowired
	IRepoReporte reporteDao;
	
	@Override
	public SuperGenericResponse testCategoria() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");

		Categoria aux = new Categoria();
		aux.setNombre("Terror");

		// insertando en la base de datos
		if (!insertCategoriaSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");

		// actualizando en la base de datos
		if (!updateCategoriaSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (!selectOneByIdCategoriaSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");

		// obteniendo todos los resgistros
		if (!getAllCategoriaSuccess())
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		// eliminado registro de la base de datos
		if (!deleteCategoriaSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	public boolean insertCategoriaSuccess(Categoria aux) {
		return categoriaDao.insert(aux).getCodigo() == Constantes.SUCCES;
	}

	public boolean updateCategoriaSuccess(Categoria aux) {
		return categoriaDao.update(aux).getCodigo() == Constantes.SUCCES;
	}

	public boolean selectOneByIdCategoriaSuccess(Categoria aux) {
		return categoriaDao.getOneById(1).getCodigo() == Constantes.SUCCES;
	}

	public boolean getAllCategoriaSuccess() {
		return categoriaDao.getAll().getCodigo() == Constantes.SUCCES;
	}

	public boolean deleteCategoriaSuccess(Categoria aux) {
		aux.setId(1);
		return categoriaDao.delete(aux).getCodigo() == Constantes.SUCCES;
	}

	@Override
	public SuperGenericResponse testVideojuego() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla videojuego::::");

		Videojuego aux = new Videojuego();
		aux.setNombre("GTA");
		aux.setCategoria(new Categoria(5));
		aux.setPrecio((float) 18.99);
		aux.setFechaDeLanzamiento(new Date());
		aux.setDesarrolladora(new Desarrolladora(1));
		aux.setImg(new Img(1));
		aux.setCantidadDisponible(99);
		aux.setPlataforma(new Plataforma(1));
		aux.setDescripcion("prueba de bd");

		// insertando en la base de datos
		if (!insertVideoJuegoSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");

		aux.setId(2);
		// actualizando en la base de datos
		if (!updateVideoJuegoSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (!selectOneByIdVideoJuegoSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");

		// obteniendo todos los resgistros
		if (!getAllVideoJuegoSuccess())
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		// eliminado registro de la base de datos
		if (!deleteVideoJuegoSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	public boolean insertVideoJuegoSuccess(Videojuego aux) {
		return videoJuegoDao.insert(aux).getCodigo() == Constantes.SUCCES;
	}

	public boolean updateVideoJuegoSuccess(Videojuego aux) {
		return videoJuegoDao.update(aux).getCodigo() == Constantes.SUCCES;
	}

	public boolean selectOneByIdVideoJuegoSuccess(Videojuego aux) {
		return videoJuegoDao.getOneById(1).getCodigo() == Constantes.SUCCES;
	}

	public boolean getAllVideoJuegoSuccess() {
		return videoJuegoDao.getAll().getCodigo() == Constantes.SUCCES;
	}

	public boolean deleteVideoJuegoSuccess(Videojuego aux) {
		return videoJuegoDao.delete(aux).getCodigo() == Constantes.SUCCES;
	}

	@Override
	public SuperGenericResponse testReporte() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla videojuego::::");

		Reporte aux = new Reporte();
		aux.setBase64("ASDAsdasd");
		aux.setFecha(new Date());
		aux.setNombre("asdasd");
		aux.setUserId(new Usuario(51));
		aux.setTipoDoc("asdas");
		aux.setNumDoc("Asdasd");
		aux.setTotal((float) 12.1);
		aux.setTotalrec((float) 12.1);
		aux.setTotalcam((float) 12.1);
		aux.setMetpag("asdasd");
		aux.setLv("asdasd");

		// insertando en la base de datos
		if (!insertReporteSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");

		aux.setId(3);
		// actualizando en la base de datos
		if (!updateReporteSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (!selectOneByIdReporteSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");

		// obteniendo todos los resgistros
		if (!getAllReporteSuccess())
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		// eliminado registro de la base de datos
		if (!deleteReporteSuccess(aux))
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	public boolean insertReporteSuccess(Reporte aux) {
		return reporteDao.insert(aux).getCodigo() == Constantes.SUCCES;
	}

	public boolean updateReporteSuccess(Reporte aux) {
		return reporteDao.update(aux).getCodigo() == Constantes.SUCCES;
	}

	public boolean selectOneByIdReporteSuccess(Reporte aux) {
		return reporteDao.getOneById(1).getCodigo() == Constantes.SUCCES;
	}

	public boolean getAllReporteSuccess() {
		return reporteDao.getAll().getCodigo() == Constantes.SUCCES;
	}

	public boolean deleteReporteSuccess(Reporte aux) {
		return reporteDao.delete(aux).getCodigo() == Constantes.SUCCES;
	}

}
