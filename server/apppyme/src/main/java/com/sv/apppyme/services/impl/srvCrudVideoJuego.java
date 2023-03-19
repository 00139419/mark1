package com.sv.apppyme.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.VideoJuegoDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoImg;
import com.sv.apppyme.repository.IRepoVideojuego;
import com.sv.apppyme.services.ICRUDVideoJuego;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.ObjectMapperUtils;

@Service
public class srvCrudVideoJuego implements ICRUDVideoJuego {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	IRepoImg ImgDao;

	@Autowired
	IRepoVideojuego videojuegoDao;

	@Override
	public SuperGenericResponse insertVideojuego(VideoJuegoDto videojuegoInfo) throws SrvValidacionException {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		
		mostrarDataRecibida(videojuegoInfo);
		
		//Insertando la imagen en la base de datos
		if(!insetarImgDelJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de guarda Img");
		
		//insertando el videojuego en la base de datos
		if(!insetarJuegoSucces(videojuegoInfo, key))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de guarda videojuego");

		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	public boolean insetarImgDelJuegoSucces(VideoJuegoDto videojuegoInfo, String key) {
		Img img = new Img();
		img.setBase64(videojuegoInfo.getImgbase64());

		if (ImgDao.insert(img).getCodigo() != Constantes.SUCCES)
			return false;
		log.info("[" + key + "]" + "::::[insetarImgDelJuegoSucces]::: Imagen insertada en base de datos correctamente:::");
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

		if(videojuegoDao.insert(vjE).getCodigo() != Constantes.SUCCES)
			return false;

		log.info("[" + key + "]" + "::::[insetarImgDelJuegoSucces]::: videojuego insertada en base de datos correctamente:::");
		return true;
	}

	@Override
	public SuperGenericResponse UpdateVideojuego() throws SrvValidacionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse DeleteVideojuego() throws SrvValidacionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse getOneByIDVideojuego() throws SrvValidacionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void mostrarDataRecibida(Object object) {
		log.info(":::: Imprimieno data recibida::::" + ObjectMapperUtils.ObjectToJsonString(object) + "::::");
	}

}
