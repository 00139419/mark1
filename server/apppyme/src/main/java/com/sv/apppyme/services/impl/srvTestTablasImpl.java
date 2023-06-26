package com.sv.apppyme.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.CabeceraFac;
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.entities.Compra;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.entities.DetalleFac;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.entities.Reporte;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.TokenOTP;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.entities.Producto;
import com.sv.apppyme.exception.SrvValidacionException;
import com.sv.apppyme.repository.IRepoCabeceraFactura;
import com.sv.apppyme.repository.IRepoCategoria;
import com.sv.apppyme.repository.IRepoCompra;
import com.sv.apppyme.repository.IRepoDesarrolladora;
import com.sv.apppyme.repository.IRepoDetalleFactura;
import com.sv.apppyme.repository.IRepoImg;
import com.sv.apppyme.repository.IRepoPlataforma;
import com.sv.apppyme.repository.IRepoReporte;
import com.sv.apppyme.repository.IRepoRol;
import com.sv.apppyme.repository.IRepoTokenOtp;
import com.sv.apppyme.repository.IRepoUser;
import com.sv.apppyme.repository.IRepoProducto;
import com.sv.apppyme.services.ITestTablas;
import com.sv.apppyme.utils.Constantes;

@Service
public class srvTestTablasImpl implements ITestTablas {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	IRepoCategoria categoriaDao;

	@Autowired
	IRepoProducto productoDao;

	@Autowired
	IRepoReporte reporteDao;
	
	@Autowired
	IRepoPlataforma plataformaDao;
	
	@Autowired
	IRepoDesarrolladora desarrolladoraDao;
	
	@Autowired
	IRepoImg imgDao;
	
	@Autowired
	IRepoRol rolDao;
	
	@Autowired
	IRepoTokenOtp tokenOtpDao;
	
	@Autowired
	IRepoUser usuarioDao;
	
	@Autowired
	IRepoCompra compraDao;
	
	@Autowired
	IRepoCabeceraFactura cabeceraFacturaDao;
	
	@Autowired
	IRepoDetalleFactura detalleFacturaDao;
	
	@Override
	public SuperGenericResponse testCategoria() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Categoria aux = new Categoria();
		aux.setNombre("Terror");

		// insertando en la base de datos
		if (categoriaDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
				List<Categoria> ls = categoriaDao.getAll().getEntity();
				if (categoriaDao.getAll().getCodigo() != Constantes.SUCCES)
					throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		// actualizando en la base de datos
		if (categoriaDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (categoriaDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (categoriaDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	@Override
	public SuperGenericResponse testProducto() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Producto aux = new Producto();
		aux.setNombre("asda");
		aux.setCategoria(new Categoria(1));
		aux.setPrecio(12.1f);
		aux.setFechaDeLanzamiento(new Date());
		aux.setDesarrolladora(new Desarrolladora(2));
		aux.setImg(new Img(1));
		aux.setCantidadDisponible(123123);
		aux.setPlataforma(new Plataforma(6));
		aux.setDescripcion("asdasd");
		aux.setFechaPublicacion(new Date());
		
		
		// insertando en la base de datos
		if (productoDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<Producto> ls = productoDao.getAll().getEntity();
		if (productoDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (productoDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (productoDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (productoDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	

	@Override
	public SuperGenericResponse testReporte() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla videojuego::::");

		Reporte aux = new Reporte();
		aux.setBase64("ASDAsdasd");
		aux.setFecha(new Date());
		aux.setNombre("asdasd");
		aux.setUserId(new User(51));
		aux.setTipoDoc("asdas");
		aux.setNumDoc("Asdasd");
		aux.setTotal((float) 12.1);
		aux.setTotalrec((float) 12.1);
		aux.setTotalcam((float) 12.1);
		aux.setMetpag("asdasd");
		aux.setLv("asdasd");

		// insertando en la base de datos
		if (reporteDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");

		// obteniendo todos los resgistros
		GenericEntityResponse<List<Reporte>> res = reporteDao.getAll();
		if (res.getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");
		
		aux.setId(res.getEntity().size());
		
		// actualizando en la base de datos
		if (reporteDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (reporteDao.getOneById(1).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");

		// eliminado registro de la base de datos
		if (reporteDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}

	@Override
	public SuperGenericResponse testPlataforma() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Plataforma aux = new Plataforma();
		aux.setNombre("Terror");
		aux.setImg(new Img(1));
		
		// insertando en la base de datos
		if (plataformaDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<Plataforma> ls = plataformaDao.getAll().getEntity();
		if (plataformaDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();

		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (plataformaDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (plataformaDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (plataformaDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	

	@Override
	public SuperGenericResponse testDesarrolladora() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Desarrolladora aux = new Desarrolladora();
		aux.setNombre("Terror");
		aux.setImg(new Img(1));
		
		// insertando en la base de datos
		if (desarrolladoraDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<Desarrolladora> ls = desarrolladoraDao.getAll().getEntity();
		if (desarrolladoraDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();

		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (desarrolladoraDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (desarrolladoraDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (desarrolladoraDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	@Override
	public SuperGenericResponse testImg() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Img aux = new Img();
		aux.setBase64("Holis");
		aux.setFecha(new Date(System.currentTimeMillis()));
		
		// insertando en la base de datos
		if (imgDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<Img> ls = imgDao.getAll().getEntity();
		if (imgDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (imgDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (imgDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (imgDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	@Override
	public SuperGenericResponse testRol() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Rol aux = new Rol();
		aux.setDescripcion("Holis");
		
		// obteniendo todos los resgistros
		List<Rol> ls = rolDao.getAll().getEntity();
		if (rolDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// obteniendo un registro por Id
		if (rolDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	@Override
	public SuperGenericResponse testTokenOtp() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		TokenOTP aux = new TokenOTP();
		aux.setEstaVerificado(true);
		aux.setEsValido(true);
		aux.setFechaDeCreacion(new Date());
		aux.setFechaDeVencimiento(new Date());
		aux.setToken("asdasd");
		aux.setUsuario(new User(5));
		
		// insertando en la base de datos
		if (tokenOtpDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<TokenOTP> ls = tokenOtpDao.getAll().getEntity();
		if (imgDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (tokenOtpDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// eliminado registro de la base de datos
		if (tokenOtpDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	@Override
	public SuperGenericResponse testUser() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		User aux = new User();
		aux.setNombre("Daniel");
		aux.setPassword("123");
		aux.setRol(new Rol(1));
		aux.setCuentaActiva(true);
		aux.setEmail("Danie");
		aux.setDepartamento("SAN SALVADOR");
		aux.setMunicipio("SOYAPANGO");
		aux.setFechaNacimiento(new Date());
		aux.setFechaEmiDoc(new Date());
		aux.setFechaVenDoc(new Date());
		aux.setGenero("Hombre");
		aux.setNumDoc("12");
		aux.setTipoDoc("12");
		aux.setImg(new Img(1));
		
		// insertando en la base de datos
		if (usuarioDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<User> ls = usuarioDao.getAll().getEntity();
		if (usuarioDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (usuarioDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (usuarioDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (usuarioDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	
	@Override
	public SuperGenericResponse testCompra() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		Compra aux = new Compra();
		aux.setFecha(new Date());
		aux.setAgente(new User(5));
		
		// insertando en la base de datos
		if (compraDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<Compra> ls = compraDao.getAll().getEntity();
		if (compraDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (compraDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (compraDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (compraDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	@Override
	public SuperGenericResponse testCabeceraFac() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		CabeceraFac aux = new CabeceraFac();
		aux.setCompra(new Compra(2));
		aux.setFecha(new Date());
		aux.setImg(new Img(1));
		aux.setMetPag("asdasd");
		aux.setNombre("DASDASD");
		aux.setNumDoc("asdasd");
		aux.setTipoDoc("asdasd");
		aux.setTotal(12.46f);
		aux.setUser(new User(5));
		
		// insertando en la base de datos
		if (cabeceraFacturaDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<CabeceraFac> ls = cabeceraFacturaDao.getAll().getEntity();
		if (cabeceraFacturaDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (cabeceraFacturaDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (cabeceraFacturaDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		//if (cabeceraFacturaDao.delete(aux).getCodigo() != Constantes.SUCCES)
		//	throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	
	
	@Override
	public SuperGenericResponse testDetalleFac() throws SrvValidacionException {
		log.info(
				"::::[INICIO]:::[testCategoria]:::: Iniciando implementacion del servicio de test tabla categoria::::");
		
		DetalleFac aux = new DetalleFac();
		aux.setCabeceraFac(new CabeceraFac(2));
		aux.setCantidad(1);
		aux.setPrecioUnitario(1.0f);
		aux.setProducto(new Producto(1));
		aux.setSubTotal(12.23f);
	
		// insertando en la base de datos
		if (detalleFacturaDao.insert(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo insertar en la base de datos°!");
		
		// obteniendo todos los resgistros
		List<DetalleFac> ls = detalleFacturaDao.getAll().getEntity();
		if (detalleFacturaDao.getAll().getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data general en la base de datos°!");

		int id = ls.size() == 0 ? 1 : ls.get(ls.size() - 1).getId();
		
		aux.setId(id);
		
		// actualizando en la base de datos
		if (detalleFacturaDao.update(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo actualizar en la base de datos°!");

		// obteniendo un registro por Id
		if (detalleFacturaDao.getOneById(id).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo obtener data unica en la base de datos°!");


		// eliminado registro de la base de datos
		if (detalleFacturaDao.delete(aux).getCodigo() != Constantes.SUCCES)
			throw new SrvValidacionException(Constantes.ERROR, "No se pudo eliminaar data en la base de datos°!");

		log.info("::::[FIN]:::[testCategoria]:::: Test completado correctamente ::::");
		return new SuperGenericResponse(Constantes.SUCCES, Constantes.OK);
	}
	

}
