package com.sv.apppyme.services.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.ComprarVideojuegoReqDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoVideojuego;
import com.sv.apppyme.services.IComprarVideoJuego;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.ObjectMapperUtils;

@Service
public class srvComprarVideoJuegoImpl implements IComprarVideoJuego {

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IRepoVideojuego videojuegoDao;

	@Override
	public SuperGenericResponse comprarVideojuego(ComprarVideojuegoReqDto compraInfo) throws SrvValidacionException {
		
		//llave que se utilizara para busqueda en los logs
		String key = compraInfo.getNumeroIdentificacion();

		//mostrando la data recibida del cliente
		mostarDataRecibida(compraInfo, key);

		// verificando que la data venga correcta
		if(!verificarDataReq(key, compraInfo))
			throw new SrvValidacionException(Constantes.ERROR, "Data viene incorrecta!");
		
		//verificando la disponibilidad de los productos
		Object[] resValidarExistencia = verificarExistenProd(compraInfo.getLv(), key);
		if(!resValidarExistencia[0].equals(Constantes.SUCCES))
			throw new SrvValidacionException(Constantes.ERROR, resValidarExistencia[1].toString());
		
		
		
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	public void mostarDataRecibida(ComprarVideojuegoReqDto compraInfo, String key) {
		log.info("[" + key + "]"
				+ "::::[INICIO]::::[comprarVideojuego]::::Iniciando servicio de comprar videojuego::::");
		log.info("[" + key + "]" + "::::[comprarVideojuego]::::Data recibida::::"
				+ ObjectMapperUtils.ObjectToJsonString(compraInfo) + "::::");
	}

	/**
	 * metodo que se encarga de verificar que la data recibidaa en la req venga
	 * correctamente
	 * @param key, compraInfo
	 * @return una bandera que indica si la data viene correcta o no
	 */
	private boolean verificarDataReq(String key, ComprarVideojuegoReqDto compraInfo) {
		log.info("[" + key + "]" + "::::[verificarDataReq]::::Se va a verificar la Data::::");

		if (compraInfo == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Data viene null::::");
			return false;
		}

		if (!compraInfo.getTipoIdentificacion().equals("DUI") || !compraInfo.getTipoIdentificacion().equals("PASAPORTE")
				|| compraInfo.getTipoIdentificacion().trim().equals("") || compraInfo.getTipoIdentificacion() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Tipo de documento de identificacion Incorrecto::::");
			return false;
		}

		if (compraInfo.getNumeroIdentificacion().replace("-", "").length() < 9
					|| compraInfo.getNumeroIdentificacion() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Dui Incorrecto::::");
			return false;
		}

		if (compraInfo.getNombre1().trim().length() < 1 || compraInfo.getNombre1() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::nombre1 incorrecto::::");
			return false;
		}

		if (compraInfo.getApellido1().trim().length() < 1 || compraInfo.getApellido1() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::apellido1 incorrecto::::");
			return false;
		}
		
		if(compraInfo.getTotal() == 0.0) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Costo total incorrecto::::");
			return false;
		}
		
		if (!compraInfo.getMetodoPago().equals("DINERO") || !compraInfo.getMetodoPago().equals("TARJETACREDITO")
				|| compraInfo.getMetodoPago().trim().equals("") || compraInfo.getMetodoPago() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Metodo de pago Incorrecto::::");
			return false;
		}
		
		if(compraInfo.getLv().isEmpty() || compraInfo.getLv() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Lista de productos viene Null::::");
			return false;
		}
		
		for(Videojuego v: compraInfo.getLv()) {
			if(v.getNombre().trim().equals("") || v.getNombre() == null) {
				log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Lista de productos Incorrecta::::");
				return false;
			}
		}
			
		log.info("[" + key + "]" + "::::[verificarDataReq]::::Data validada correctamente!::::");
		return true;
	}
	
	/**
	 * Metodo que se encarga de verificar que existen disponnibilidad de los productos en la base de datos
	 * @param ls, key
	 * @return un arreglo donde la primera posicion es la afirmacion o negacion de la existencia de productos y la segunda el producto que no esta disponible, si es que lo hubiera
	 * @author dm420
	 */
	public Object[] verificarExistenProd(List<Videojuego> ls, String key) {
		log.info("[" + key + "]" + "::::[verificarExistenProd]::::Verificando la disponibilidad de los productos!::::");
		
		Object[] res = {-1, "Error generico!"};
		
		HashMap< String, Integer> cantidadDisponibles = new HashMap<>();
		List<Videojuego> juegosDisponibles = videojuegoDao.getAll().getEntity();
		
		for(Videojuego v: juegosDisponibles) {
			cantidadDisponibles.put(v.getNombre(), v.getCantidadDisponible());
		}
		
		for(Videojuego v:ls) {
			if(cantidadDisponibles.get(v.getNombre()) > 0) {
				Integer cantidadActualDeProdIndividual = cantidadDisponibles.get(v.getNombre());
				cantidadDisponibles.replace(v.getNombre(), cantidadActualDeProdIndividual - 1);
			}else {
				log.info("[" + key + "]" + "::::[verificarExistenProd::::[ERROR]::::El producto + " + v.getNombre() + "No cuenta con la suficiente disponibilidad!::::");
				res[0] = -1;
				res[1] = "El producto " + v.getNombre() + " no cuenta con la cantidad suficiente de almacen";
				return res;
			}
		}
		
		log.info("[" + key + "]" + "::::[verificarExistenProd]::::Todos los productos dispoibles!::::");
		return res;
	}
	
	public boolean realizarComprar(List<Videojuego> ls, String key) {
		
		
		return true;
	}
}
