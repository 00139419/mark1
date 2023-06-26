package com.sv.apppyme.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sv.apppyme.controllers.dto.ComprarVideojuegoReqDto;
import com.sv.apppyme.controllers.dto.FacturaResDto;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Reporte;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.entities.Producto;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.reports.dto.FacturaDto;
import com.sv.apppyme.reports.repository.IReportManagerJasper;
import com.sv.apppyme.repository.IRepoReporte;
import com.sv.apppyme.repository.IRepoUser;
import com.sv.apppyme.repository.IRepoProducto;
import com.sv.apppyme.services.IComprarVideoJuego;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Log4jUtils;
import com.sv.apppyme.utils.ObjectMapperUtils;

@Service
public class srvComprarVideoJuegoImpl implements IComprarVideoJuego {

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IRepoProducto videojuegoDao;
	
	@Autowired
	IRepoUser usuarioDao;
	
	@Autowired
	IRepoReporte reporteDao;
	
	@Autowired
	IReportManagerJasper jasperReportManager;

	@Override
	public GenericEntityResponse<FacturaResDto> comprarVideojuego(ComprarVideojuegoReqDto compraInfo) throws SrvValidacionException {
		GenericEntityResponse<FacturaResDto> res = new GenericEntityResponse<>();
		Reporte reporte = null;
		String pdf = null;
		Date fechaTransaccion = new Date(System.currentTimeMillis());
		
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
		
		//creando y guardando factura
		reporte = crearObjetoReporte(compraInfo, fechaTransaccion);
		pdf = guardarFactura(reporte, key);
		
		//actualizando la cantidad de productos disponibles en la base de datos
		if(!actualizarCantidadProd(compraInfo.getLv(), key))
			throw new SrvValidacionException(Constantes.ERROR, "Error actualizando la cantidad de productos en laa base de datos");
		
		res.setCodigo(Constantes.SUCCES);
		res.setMensaje(Constantes.OK);
		res.setEntity(new FacturaResDto(pdf));
		return res;
	}
	
	/**
	 * metodo para mostrar la data recibida del cliente 
	 * @param compraInfo
	 * @param key
	 */
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
		
		if ((!compraInfo.getTipoIdentificacion().equals("DUI") && !compraInfo.getTipoIdentificacion().equals("PASAPORTE"))
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
		
		if ((!compraInfo.getMetodoPago().equals("DINERO") && !compraInfo.getMetodoPago().equals("TARJETACREDITO"))
				|| compraInfo.getMetodoPago().trim().equals("") || compraInfo.getMetodoPago() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Metodo de pago Incorrecto::::");
			return false;
		}
		
		if(compraInfo.getLv().isEmpty() || compraInfo.getLv() == null) {
			log.info("[" + key + "]" + "::::[verificarDataReq]::::[ERROR]::::Lista de productos viene Null::::");
			return false;
		}
		
		for(Producto v: compraInfo.getLv()) {
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
	public Object[] verificarExistenProd(List<Producto> ls, String key) {
		log.info("[" + key + "]" + "::::[verificarExistenProd]::::Verificando la disponibilidad de los productos!::::");
		
		Object[] res = {-1, "Error generico!"};
		
		HashMap< String, Integer> cantidadDisponibles = new HashMap<>();
		List<Producto> juegosDisponibles = videojuegoDao.getAll().getEntity();
		
		for(Producto v: juegosDisponibles) {
			cantidadDisponibles.put(v.getNombre(), v.getCantidadDisponible());
		}
		
		if(ls.isEmpty()) {
			log.info("[" + key + "]" + "::::[verificarExistenProd::::[ERROR]::::No hay productos en la base de datos::::");
			res[0] = Constantes.ERROR;
			res[1] = "No hay productos en la base de datos!";
			return res;
		}
		
		for(Producto v:ls) {
			if(cantidadDisponibles.get(v.getNombre()) > 0) {
				Integer cantidadActualDeProdIndividual = cantidadDisponibles.get(v.getNombre());
				cantidadDisponibles.replace(v.getNombre(), cantidadActualDeProdIndividual - 1);
			}else {
				log.info("[" + key + "]" + "::::[verificarExistenProd::::[ERROR]::::El producto + " + v.getNombre() + "No cuenta con la suficiente disponibilidad!::::");
				res[0] = Constantes.ERROR;
				res[1] = "El producto " + v.getNombre() + " no cuenta con la cantidad suficiente de almacen";
				return res;
			}
		}
		res[0] = Constantes.SUCCES;
		res[1] = "Todos los productos estan disponibles";
		log.info("[" + key + "]" + "::::[verificarExistenProd]::::Todos los productos dispoibles!::::");
		return res;
	}
	
	/**
	 * metodo para actualizar la cantidad de productos que exitse en stock despues de la compra 
	 * @param ls
	 * @param key
	 * @return
	 * @throws SrvValidacionException
	 */
	public boolean actualizarCantidadProd(List<Producto> ls, String key) throws SrvValidacionException {
		HashMap<String, Integer> productosComprados = obtenerProductosComprados(ls, key);
		
		try {
				productosComprados.forEach((k,v) -> {
				Producto aux = videojuegoDao.getOneByNombre(k).getEntity();
				aux.setCantidadDisponible(aux.getCantidadDisponible() - v);
				
				if(videojuegoDao.update(aux).getCodigo() != Constantes.SUCCES)
					log.info("[" + key + "]" + "::::[realizarComprar]::::[ERROR]::::Error actualizando producto " + k + "::::");
			});
		} catch (Exception e) {
			log.info("[" + key + "]" + "::::[realizarComprar]::::[ERROR]:::::Error generico actualizando la cantidad de productos disponibles::::");
			log.info(Log4jUtils.getStackTrace(e));
			return false;
		}
		
		log.info("[" + key + "]" + "::::[verificarExistenProd]::::Cantidad de productos disponibles actuaalñizados correctamente!::::");
		return true;
	}
	
	/**
	 * metodo que se encarga de enlistar los productos comprado y su cantidades
	 * @param ls
	 * @param key
	 * @return un hashMap con los productos listados y sus cantidades compradas
	 */
	public HashMap<String, Integer> obtenerProductosComprados(List<Producto> ls, String key){
		HashMap<String, Integer> productosComprados = new HashMap<>();
		
		for(Producto v: ls) {
			if(productosComprados.containsKey(v.getNombre())) {
				productosComprados.replace(v.getNombre(), productosComprados.get(v.getNombre()) + 1);
			}else {
				productosComprados.put(v.getNombre(), 1);
			}
		}
		
		return productosComprados;
	}
	
	/**
	 * Metodo para crear y guardar la factura en la base de datos
	 * @param reporte
	 * @param key
	 * @return
	 * @throws SrvValidacionException
	 */
	public String guardarFactura(Reporte reporte, String key) throws SrvValidacionException{
		
		String pdf = "";
		Reporte aux = null;
		
		if(reporteDao.insert(reporte).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de guardar reporte en la base de datos!");
		
		aux = reporteDao.getOneByFecha(reporte.getFecha()).getEntity();
		if(aux == null)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de obtener reporte por fecha de ingreso!");
		
		reporte.setId(aux.getId());
		pdf = crearPdf(reporte, key);
		if(pdf.equals(""))
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de creaer base64 de factura!");
		
		reporte.setBase64(pdf);
		if(reporteDao.update(reporte).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "Error en el proceso de actualizar el reporte con base 64");
		
		return pdf;
	}
	
	/**
	 * metodo que crea un objecto de tipo reporte con la data recibida del front para insertarlo en la base de datos
	 * @param compraInfo
	 * @param fechaTransaccion
	 * @return
	 * @throws SrvValidacionException
	 */
	public Reporte crearObjetoReporte(ComprarVideojuegoReqDto compraInfo, Date fechaTransaccion) throws SrvValidacionException{
		Reporte reporte = new Reporte();
		reporte.setFecha(fechaTransaccion);
		reporte.setLv(ObjectMapperUtils.ObjectToJsonString(compraInfo.getLv()));
		reporte.setMetpag(compraInfo.getMetodoPago());
		reporte.setNombre(nombreConcatenado(compraInfo));
		reporte.setTipoDoc(compraInfo.getTipoIdentificacion());
		reporte.setNumDoc(compraInfo.getNumeroIdentificacion());
		reporte.setTotal( (float) compraInfo.getTotal());
		reporte.setTotalcam( (float) compraInfo.getTotalDineroFisicoCambio());
		reporte.setTotalrec( (float) compraInfo.getTotalDineroFisicoRecibido());
		reporte.setUserId(obtenerUsuarioAsignador());
		return reporte;
	}
	
	/**
	 * metodo que concatena el nombre del comprador en una sola variable
	 * @param compraInfo
	 * @return
	 */
	public static String nombreConcatenado(ComprarVideojuegoReqDto compraInfo) {
		return 	compraInfo.getNombre1() + " " +
				compraInfo.getNombre2() + " " +
				compraInfo.getApellido1() + " " +
				compraInfo.getApellido2();
	}
	
	/**
	 * meetodo para obtener al usuario Asignador
	 * @return Usuario asginador
	 */
	public User obtenerUsuarioAsignador() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return usuarioDao.getOneByEmail(username).getEntity();
	}
	
	/**
	 * Metodo para crear la factura y convertirla en base64
	 * @param reporte
	 * @param key
	 * @return base64 de la factura
	 */
	public String crearPdf(Reporte reporte, String key) {
		String pdf = "";
		FacturaDto factura = new FacturaDto();
		factura.setId(reporte.getId());
		factura.setTipoDoc(reporte.getTipoDoc());
		factura.setFecha(reporte.getFecha().toString());
		factura.setNombreCompleto(reporte.getNombre());
		factura.setTotal(String.valueOf(reporte.getTotal()));
		factura.setUser(obtenerUsuarioAsignador().getEmail());
		factura.setMetPag(reporte.getMetpag());
		factura.setNumDoc(String.valueOf(reporte.getNumDoc()));
		
		List<Object> ls = new ArrayList<>();
		ls.add(factura);
		
		pdf = jasperReportManager.generatedBase64JasperReport(ls, "factura");
		
		return pdf;
	}
}
