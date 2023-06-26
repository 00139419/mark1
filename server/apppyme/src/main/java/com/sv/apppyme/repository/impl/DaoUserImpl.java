package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.date.DateUtilities;
import org.springframework.stereotype.Repository;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Booking;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.repository.IRepoUser;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.Log4jUtils;

@Repository
public class DaoUserImpl implements IRepoUser {
	
	Logger log = Logger.getLogger(DaoUserImpl.class);
	
	//nombre de la tabla
	public static final String DB_TABLA_USER = "user";
	
	//columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_EMAIL = "email";
	public static final String COL_PASSWORD = "password";
	public static final String COL_ROL_ID = "rol_id";
	public static final String COL_CUENTA_ACTIVA = "cuentaactiva";
	public static final String COL_IMAGEN_ID = "img_id";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_DEPARTAMENTO = "departamento";
	public static final String COL_MUNICIPIO = "municipio";
	public static final String COL_FECHA_NACIMIENTO = "fechanacimiento";
	public static final String COL_EMISION_DUI = "fechaemidoc";
	public static final String COL_VENCIMIENTO_DUI = "fechavendoc";
	public static final String COL_GENERO = "genero";
	public static final String COL_NUMERO_DOCUMENTO = "numdoc";
	public static final String COL_TIPO_DOCUMENTO = "tipodoc";

	
	//consultas de la tabla
	public static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM \"" + DB_TABLA_USER + "\""
			+ " WHERE " 
				+ COL_EMAIL + " = ?";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM \"" + DB_TABLA_USER + "\" WHERE " +  COL_ID + "= ? ";
	public static final String SQL_INSERT = "INSERT INTO \"" + DB_TABLA_USER + "\" "
			+ "(" 
				+ COL_EMAIL + ", "
				+ COL_PASSWORD +", " 
				+ COL_ROL_ID + ", "
				+ COL_CUENTA_ACTIVA + ", "
				+ COL_IMAGEN_ID + ", "
				+ COL_NOMBRE +  ", "
				+ COL_DEPARTAMENTO + ", "
				+ COL_MUNICIPIO + ", "
				+ COL_FECHA_NACIMIENTO + ", "
				+ COL_EMISION_DUI + ", "
				+ COL_VENCIMIENTO_DUI + ", "
				+ COL_GENERO + ", "
				+ COL_NUMERO_DOCUMENTO + ", "
				+ COL_TIPO_DOCUMENTO
			+ " )"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_UPDATE = "UPDATE \"" + DB_TABLA_USER 
			+ "\" SET " 
				+  COL_EMAIL +  " = ?, "  
				+  COL_PASSWORD +  " = ?, "  
				+  COL_ROL_ID +  " = ?, "
				+  COL_CUENTA_ACTIVA +  " = ?, "
				+  COL_IMAGEN_ID +  " = ? ,"
				+  COL_NOMBRE +  " = ?, "
				+  COL_DEPARTAMENTO +  " = ?, "
				+  COL_MUNICIPIO +  " = ?, "
				+  COL_FECHA_NACIMIENTO +  " = ?, "
				+  COL_EMISION_DUI +  " = ?, "
				+  COL_VENCIMIENTO_DUI +  " = ?, "
				+  COL_GENERO +  " = ?, "
				+  COL_NUMERO_DOCUMENTO +  " = ?, "
				+  COL_TIPO_DOCUMENTO +  " = ? "
			+ "WHERE "
				+ COL_ID + " = ?" ;
	public static final String SQL_SELECT = "SELECT * FROM \"" + DB_TABLA_USER + "\"";
	public static final String SQL_DELETE = "DELETE FROM \"" + DB_TABLA_USER + "\" WHERE " + COL_ID + " = ?";
	
	@Override
	public SuperGenericResponse insert(User usuario) {
		log.info("::::[Incio]::::[insert]::::Iniciando implementacion del DAO para los usuarios::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insert]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insert]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, usuario.getEmail());
			log.info("::::[insert]::::Valor ____________________ 1::::Email:::Value:::" + usuario.getEmail() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[insert]::::Valor ____________________ 2::::Password:::Value:::" + usuario.getPassword() + ":::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[insert]::::Valor ____________________ 3::::Rol:::Value:::" + usuario.getRol().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, false);
			log.info("::::[insert]::::Valor ____________________ 4::::cuentaActiva:::Value:::" + false + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(5, usuario.getImg().getId());
			log.info("::::[insert]::::Valor ____________________ 5:::: ImgId :::Value:::" + usuario.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(6, usuario.getNombre());
			log.info("::::[insert]::::Valor ____________________ 6::::Nombre:::Value:::" + usuario.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(7, usuario.getDepartamento());
			log.info("::::[insert]::::Valor ____________________ 7::::Departamento:::Value:::" + usuario.getDepartamento() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(8, usuario.getMunicipio());
			log.info("::::[insert]::::Valor ____________________ 8:::: Municipio :::Value:::" + usuario.getMunicipio() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(9, DateUtils.convertDateToTimeStamp(usuario.getFechaNacimiento()));
			log.info("::::[insert]::::Valor ____________________ 9:::: Fecha nacimiento :::Value:::" + DateUtils.convertDateToTimeStamp(usuario.getFechaNacimiento()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(10, DateUtils.convertDateToTimeStamp(usuario.getFechaEmiDoc()));
			log.info("::::[insert]::::Valor ____________________ 10:::: FechaEmiDoc :::Value:::" +  DateUtils.convertDateToTimeStamp(usuario.getFechaEmiDoc()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(11, DateUtils.convertDateToTimeStamp(usuario.getFechaVenDoc()));
			log.info("::::[insert]::::Valor ____________________ 11:::: FechaVenDui :::Value:::" + DateUtils.convertDateToTimeStamp(usuario.getFechaVenDoc()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(12, usuario.getGenero());
			log.info("::::[insert]::::Valor ____________________ 12:::: genero :::Value:::" + usuario.getGenero() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(13, usuario.getNumDoc());
			log.info("::::[insert]::::Valor ____________________ 13:::: NumDoc:::Value:::" + usuario.getNumDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(14, usuario.getTipoDoc());
			log.info("::::[insert]::::Valor ____________________ 14:::: TipoDoc :::Value:::" + usuario.getTipoDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[insert]:::SQL generado:::" + stmt.toString() + "::::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[insert]::::stmt ejecutado correctamente::::");
			log.info("::::[insert]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				res.setCodigo(Constantes.SUCCES);
				res.setMensaje(Constantes.OK);
				log.info("::::[insert]::::Se crearon " + resultado + " nuevos registros\"::::");
			}
			log.info("::::[insert]::::Fin interpretando Data recibida::::");
			stmt.close();
			log.info("::::[insert]::::PreparedStatement CERRADO correctamente::::");
			con.close();
			log.info("::::[insert]::::Conexion CERRADO correctamente::::");
			log.info("::::[insert]::::Enviando repsuesta del implementacion del DAO::::");
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[insert]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insert]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insert]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insert]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[insertar]::::Fin implementacion del DAO::::");
		return res;
	}

	@Override
	public GenericEntityResponse<User> getOneByEmail(String email) {
		log.info("::::[Incio]::::[getOneByUsername]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<User> res = new GenericEntityResponse<>();
		try {
			User usuario = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneByUsername]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_EMAIL);
			log.info("::::[selectByUsername]::::PreparedStatment CREADO correctamente::::");
			stmt.setString(1, email);
			log.info("::::[getOneByUsername]::::Valor ____________________ 1::::Email:::Value:::" + email + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneByUsername]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneByUsername]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneByUsername]::::Interpretando Data recibida::::");
			while(rs.next()) {
				usuario = new User();
				usuario.setId(rs.getInt(COL_ID));
				usuario.setEmail(rs.getString(COL_EMAIL));
				usuario.setPassword(rs.getString(COL_PASSWORD));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setCuentaActiva(rs.getBoolean(COL_CUENTA_ACTIVA));
				usuario.setImg(new Img(rs.getInt(COL_IMAGEN_ID)));
				usuario.setNombre(rs.getString(COL_NOMBRE));
				usuario.setDepartamento(rs.getString(COL_DEPARTAMENTO));
				usuario.setMunicipio(rs.getString(COL_MUNICIPIO));
				usuario.setFechaNacimiento(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA_NACIMIENTO)));
				usuario.setFechaEmiDoc(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_EMISION_DUI)));
				usuario.setFechaVenDoc(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_VENCIMIENTO_DUI)));
				usuario.setGenero(rs.getString(COL_GENERO));
				usuario.setNumDoc(rs.getString(COL_NUMERO_DOCUMENTO));
				usuario.setTipoDoc(rs.getString(COL_TIPO_DOCUMENTO));
			}
			log.info("::::[getOneByUsername]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getOneByUsername]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getOneByUsername]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getOneByUsername]::::Conexion CERRADO correctamente::::");
			log.info("::::[getOneByUsername]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			res.setEntity(usuario);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getOneByUsername]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneByUsername]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneByUsername]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getOneByUsername]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneByUsername]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneByUsername]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}

	@Override
	public GenericEntityResponse<User> getOneById(int id) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<User> res = new GenericEntityResponse<>();
		try {
			User usuario = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_ID);
			log.info("::::[getOneById]::::PreparedStatment CREADO correctamente::::");
			stmt.setInt(1, id);
			log.info("::::[getOneById]::::Valor ____________________ 1::::id:::Value:::" + id + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				usuario = new User();
				usuario.setId(rs.getInt(COL_ID));
				usuario.setEmail(rs.getString(COL_EMAIL));
				usuario.setPassword(rs.getString(COL_PASSWORD));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setCuentaActiva(rs.getBoolean(COL_CUENTA_ACTIVA));
				usuario.setImg(new Img(rs.getInt(COL_IMAGEN_ID)));
				usuario.setNombre(rs.getString(COL_NOMBRE));
				usuario.setDepartamento(rs.getString(COL_DEPARTAMENTO));
				usuario.setMunicipio(rs.getString(COL_MUNICIPIO));
				usuario.setFechaNacimiento(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA_NACIMIENTO)));
				usuario.setFechaEmiDoc(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_EMISION_DUI)));
				usuario.setFechaVenDoc(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_VENCIMIENTO_DUI)));
				usuario.setGenero(rs.getString(COL_GENERO));
				usuario.setNumDoc(rs.getString(COL_NUMERO_DOCUMENTO));
				usuario.setTipoDoc(rs.getString(COL_TIPO_DOCUMENTO));
			}
			log.info("::::[getOneById]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getOneById]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getOneById]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getOneById]::::Conexion CERRADO correctamente::::");
			log.info("::::[getOneById]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			res.setEntity(usuario);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getOneById]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getOneById]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}

	@Override
	public SuperGenericResponse update(User usuario) {
		log.info("::::[Incio]::::[update]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_UPDATE);
			log.info("::::[update]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[update]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, usuario.getEmail());
			log.info("::::[update]::::Valor ____________________ 1::::Email:::Value:::" + usuario.getEmail() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[update]::::Valor ____________________ 2::::Password:::Value:::" + usuario.getPassword() + ":::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[insert]::::Valor ____________________ 3::::Rol:::Value:::" + usuario.getRol().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, false);
			log.info("::::[update]::::Valor ____________________ 4::::cuentaActiva:::Value:::" + false + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(5, usuario.getImg().getId());
			log.info("::::[update]::::Valor ____________________ 5:::: ImgId :::Value:::" + usuario.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(6, usuario.getNombre());
			log.info("::::[update]::::Valor ____________________ 6::::Email:::Value:::" + usuario.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(7, usuario.getDepartamento());
			log.info("::::[update]::::Valor ____________________ 7::::Departamento:::Value:::" + usuario.getDepartamento() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(8, usuario.getMunicipio());
			log.info("::::[update]::::Valor ____________________ 8:::: Municipio :::Value:::" + usuario.getMunicipio() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(9, DateUtils.convertDateToTimeStamp(usuario.getFechaNacimiento()));
			log.info("::::[update]::::Valor ____________________ 9:::: Fecha nacimiento :::Value:::" + DateUtils.convertDateToTimeStamp(usuario.getFechaNacimiento()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(10, DateUtils.convertDateToTimeStamp(usuario.getFechaEmiDoc()));
			log.info("::::[update]::::Valor ____________________ 10:::: FechaEmiDoc :::Value:::" +  DateUtils.convertDateToTimeStamp(usuario.getFechaEmiDoc()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(11, DateUtils.convertDateToTimeStamp(usuario.getFechaVenDoc()));
			log.info("::::[update]::::Valor ____________________ 11:::: FechaVenDui :::Value:::" + DateUtils.convertDateToTimeStamp(usuario.getFechaVenDoc()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(12, usuario.getGenero());
			log.info("::::[insert]::::Valor ____________________ 12:::: genero :::Value:::" + usuario.getGenero() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(13, usuario.getNumDoc());
			log.info("::::[update]::::Valor ____________________ 13:::: NumDoc:::Value:::" + usuario.getNumDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(14, usuario.getTipoDoc());
			log.info("::::[update]::::Valor ____________________ 14:::: TipoDoc :::Value:::" + usuario.getTipoDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(15, usuario.getId());
			log.info("::::[update]::::Valor ____________________ 15:::: ID :::Value:::" + usuario.getId() + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[update]:::SQL generado:::" + stmt.toString() + "::::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[update]::::stmt ejecutado correctamente::::");
			log.info("::::[update]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				res.setCodigo(Constantes.SUCCES);
				res.setMensaje(Constantes.OK);
				log.info("::::[update]::::Se modificaron " + resultado + " registros::::");
				log.info("::::[update]::::Enviando repsuesta del implementacion del DAO::::");
			}
			log.info("::::[update]::::Fin interpretando Data recibida::::");
			stmt.close();
			log.info("::::[update]::::PreparedStatement CERRADO correctamente::::");
			con.close();
			log.info("::::[update]::::Conexion CERRADO correctamente::::");
			log.info("::::[update]::::Enviando repsuesta del implementacion del DAO::::");
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[update]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[update]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[update]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
		} catch (Exception e) {
			log.info("::::[ERROR]::::[update]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[update]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[update]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[update]::::Fin implementacion del DAO::::");
		return res;
	}

	@Override
	public GenericEntityResponse<List<User>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<List<User>> res = new GenericEntityResponse<>();
		try {
			List<User> ls = new ArrayList<>();
			User usuario = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getAll]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT);
			log.info("::::[getAll]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getAll]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getAll]::::ResultSet CREADO correctamente::::");
			log.info("::::[getAll]::::Interpretando Data recibida::::");
			while(rs.next()) {
				usuario = new User();
				usuario.setId(rs.getInt(COL_ID));
				usuario.setEmail(rs.getString(COL_EMAIL));
				usuario.setPassword(rs.getString(COL_PASSWORD));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setCuentaActiva(rs.getBoolean(COL_CUENTA_ACTIVA));
				usuario.setImg(new Img(rs.getInt(COL_IMAGEN_ID)));
				usuario.setNombre(rs.getString(COL_NOMBRE));
				usuario.setDepartamento(rs.getString(COL_DEPARTAMENTO));
				usuario.setMunicipio(rs.getString(COL_MUNICIPIO));
				usuario.setFechaNacimiento(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA_NACIMIENTO)));
				usuario.setFechaEmiDoc(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_EMISION_DUI)));
				usuario.setFechaVenDoc(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_VENCIMIENTO_DUI)));
				usuario.setGenero(rs.getString(COL_GENERO));
				usuario.setNumDoc(rs.getString(COL_NUMERO_DOCUMENTO));
				usuario.setTipoDoc(rs.getString(COL_TIPO_DOCUMENTO));
				ls.add(usuario);
			}
			log.info("::::[getAll]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getAll]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getAll]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getAll]::::Conexion CERRADO correctamente::::");
			log.info("::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			res.setEntity(ls);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getAll]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getAll]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}
	
	
	@Override
	public SuperGenericResponse delete(User user) {
		log.info("::::[INCIO]::::[delete]::::Incio de DAO::::");
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[delete]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_DELETE);
			log.info("::::[delete]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, user.getId());
			log.info("::::[delete]::::Valor ____________________ 1::::id:::Value:::" + user.getId() + "Seteado CORRECTAMENTE::::");
			log.info("::::[delete]:::SQL generado:::" + stmt.toString() + "::::");
			int rs = ConexionPostgres.updateQuery(stmt);
			log.info("::::[delete]::::Datos guardado correctamente::::");
			log.info("::::[delete]::::Cantidad de datos eliminados::::value::::" + rs + "::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			log.info("::::[delete]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[delete]::::Error generico en el DAO::::");
			log.info("::::[ERROR]::::[delete]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[delete]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[delete]::::Fin de DAO::::");
		return res;
	}
	
	
	
	

}
