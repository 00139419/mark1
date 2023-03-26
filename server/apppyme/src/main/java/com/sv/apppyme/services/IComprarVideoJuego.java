package com.sv.apppyme.services;

import com.sv.apppyme.controllers.dto.ComprarVideojuegoReqDto;
import com.sv.apppyme.controllers.dto.FacturaResDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IComprarVideoJuego {
	
	/**
	 * 
	 * Metodo que se encagar de hacer las comprar de los productos
	 * 
	 * @return una repuesta generica con el status de la transaccion/compra y el base64 de la factura
	 * @throws SrvValidacionException
	 * @author dm420
	 */
	GenericEntityResponse<FacturaResDto> comprarVideojuego(ComprarVideojuegoReqDto compraInfo) throws SrvValidacionException;
}
