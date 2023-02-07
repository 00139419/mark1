package com.sv.apppyme.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sv.apppyme.dao.IRolDao;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.utils.Constantes;

public class srvDataImpl implements IData {

	@Autowired
	IRolDao srvRolImpl; 
	
	Logger log = Logger.getLogger(srvDataImpl.class);
	
	
	@Override
	public GenericEntityResponse<List<Rol>> getAllRoles() throws SrvValidacionException {
		log.info("::::[INICIO]::::[getAllRoles]:::Inicinado implementacion del servicio para obtener los roles::::");
		GenericEntityResponse<List<Rol>> resDao = srvRolImpl.getAll();
		log.info("::::[getAllRoles]:::Respuesta obtenida del DAO::::");
		log.info("::::[getAllRoles]:::codigo::::" + resDao.getCodigo() + "::::");
		log.info("::::[getAllRoles]:::mensaje::::" + resDao.getMensaje() + "::::");
		log.info("::::[getAllRoles]:::entity::::" + resDao.getEntity().toString() + "::::");
		log.info("::::[getAllRoles]:::Lista<E>::::" + resDao.getListaEntity().toString() + "::::");
		if(resDao.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, resDao.getMensaje());
		log.info("::::[getAllRoles]:::Retornando respuesta::::");
		log.info("::::[FIN]::::[getAllRoles]:::Fin implementacion del servicio para obtener los roles::::");
		return resDao;
	}

}
