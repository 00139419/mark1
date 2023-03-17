package com.sv.apppyme.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.ComprarVideojuegoReqDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.services.IData;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.ObjectMapperUtils;

@Service
public class srvDataImpl implements IData {

	Logger log = Logger.getLogger(getClass());

	@Override
	public SuperGenericResponse comprarVideojuego(ComprarVideojuegoReqDto compraInfo) throws SrvValidacionException {
		String key = compraInfo.getNumeroIdentificacion();

		log.info("[" + key + "]"
				+ "::::[INICIO]::::[comprarVideojuego]::::Iniciando servicio de comprar videojuego::::");
		log.info("[" + key + "]" + "::::[comprarVideojuego]::::Data recibida::::"
				+ ObjectMapperUtils.ObjectToJsonString(compraInfo) + "::::");

		// verificando que la data venga correcta
		log.info("[" + key + "]" + "::::[comprarVideojuego]::::Se va a verificar la Data::::");
		boolean dataCorrecta = verificarDataReq(key, compraInfo);
		
		if(!dataCorrecta)
			throw new SrvValidacionException(Constantes.ERROR, "Data viene incorrecta!");
		
		//verificando la disponibilidad de los productos
		
		return null;
	}

	/**
	 * 
	 * metodo que se encarga de verificar que la data recibidaa en la req venga
	 * correctamente
	 * @param key, compraInfo
	 * @return una bandera que indica si la data viene correcta o no
	 */
	private boolean verificarDataReq(String key, ComprarVideojuegoReqDto compraInfo) {

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

}
