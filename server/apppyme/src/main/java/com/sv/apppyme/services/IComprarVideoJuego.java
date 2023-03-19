package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.ComprarVideojuegoReqDto;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IComprarVideoJuego {
	
	/**
	 * 
	 * Metodo que se encagar de hacer las comprar de los productos
	 * 
	 * @return una repuesta generica con el status de la transaccion/compra
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	SuperGenericResponse comprarVideojuego(ComprarVideojuegoReqDto compraInfo) throws SrvValidacionException;
}
