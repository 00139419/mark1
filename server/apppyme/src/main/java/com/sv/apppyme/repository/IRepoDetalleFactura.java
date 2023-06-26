package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.DetalleFac;

public interface IRepoDetalleFactura {

	/* 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion INSERT en la base de datos
	 */
	SuperGenericResponse insert(DetalleFac detalleFactura);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion UPDATE en la base de datos para cierto registro
	 */
	SuperGenericResponse update(DetalleFac detalleFactura);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return una respuesta con el status de la operacion DELETE en la base de datos para cierto registro
	 */
	SuperGenericResponse delete(DetalleFac detalleFactura);
	
	/**
	 * 
	 * @param
	 * @author dm420
	 * @return un registro de la base de datos, a traves de su ID
	 */
	GenericEntityResponse<DetalleFac> getOneById(int id);
	
	/**
	 * 
	 * @author dm420
	 * @return todos los registros de la tabla en la base de datos
	 */
	GenericEntityResponse<List<DetalleFac>> getAll();

}
