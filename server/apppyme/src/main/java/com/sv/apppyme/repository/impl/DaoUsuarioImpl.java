package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.repository.IRepoUsuario;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.Log4jUtils;

@Repository
public class DaoUsuarioImpl implements IRepoUsuario {
	
	Logger log = Logger.getLogger(DaoUsuarioImpl.class);
	
	//nombre de la tabla
	public static final String DB_TABLA_USUARIO = "usuario";
	
	//columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_PASSWORD = "password";
	public static final String COL_ROL_ID = "rol_id";
	public static final String COL_CUENTA_ACTIVA = "cuentaacti	va";
	public static final String COL_EMAIL = "email";
	public static final String COL_DEPARTAMENTO = "departamento";
	public static final String COL_MUNICIPIO = "municipio";
	public static final String COL_FECHA_NACIMIENTO = "fechanacimiento";
	public static final String COL_EMISION_DUI = "fechaemidoc";
	public static final String COL_VENCIMIENTO_DUI = "fechavendoc";
	public static final String COL_GENERO = "genero";
	public static final String COL_NUMERO_DOCUMENTO = "numdoc";
	public static final String COL_TIPO_DOCUMENTO = "tipodoc";
	public static final String COL_IMAGEN_ID = "img_id";
	
	
	//consultas de la tabla
	public static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM " + DB_TABLA_USUARIO 
			+ " WHERE " 
				+ COL_EMAIL + " = ?";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_USUARIO + " WHERE " +  COL_ID + "= ? ";
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_USUARIO 
			+ "( " 
				+  COL_NOMBRE +  ", " 
				+ COL_PASSWORD +", " 
				+ COL_ROL_ID + ", "
				+ COL_CUENTA_ACTIVA + ", "
				+ COL_EMAIL + ", "
				+ COL_DEPARTAMENTO + ", "
				+ COL_MUNICIPIO + ", "
				+ COL_FECHA_NACIMIENTO + ", "
				+ COL_EMISION_DUI + ", "
				+ COL_VENCIMIENTO_DUI + ", "
				+ COL_GENERO + ", "
				+ COL_NUMERO_DOCUMENTO + ", "
				+ COL_TIPO_DOCUMENTO + ", "
				+ COL_IMAGEN_ID
			+ " )"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_USUARIO 
			+ " SET " 
				+  COL_NOMBRE +  " = ?, "  
				+  COL_PASSWORD +  " = ?, "  
				+  COL_ROL_ID +  " = ?, "
				+  COL_CUENTA_ACTIVA +  " = ?, "
				+  COL_EMAIL +  " = ?, "
				+  COL_DEPARTAMENTO +  " = ?, "
				+  COL_MUNICIPIO +  " = ?, "
				+  COL_FECHA_NACIMIENTO +  " = ?, "
				+  COL_EMISION_DUI +  " = ?, "
				+  COL_VENCIMIENTO_DUI +  " = ?, "
				+  COL_GENERO +  " = ?, "
				+  COL_NUMERO_DOCUMENTO +  " = ?, "
				+  COL_TIPO_DOCUMENTO +  " = ?, "
				+  COL_IMAGEN_ID +  " = ? "
			+ "WHERE "
				+ COL_ID + " = ?" ;
	
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
			log.info("::::[insert]::::Valor ____________________ 1::::Nombre:::Value:::" + usuario.getEmail() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[insert]::::Valor ____________________ 2::::Password:::Value:::" + usuario.getPassword() + ":::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[insert]::::Valor ____________________ 3::::Rol:::Value:::" + usuario.getRol().getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, false);
			log.info("::::[insert]::::Valor ____________________ 4::::cuentaActiva:::Value:::" + false + "::::" + "Seteado CORRECTAMENTE:::");
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
				usuario.setEmail(rs.getString(COL_NOMBRE));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setPassword(rs.getString(COL_PASSWORD));
				usuario.setCuentaActiva(rs.getBoolean(COL_CUENTA_ACTIVA));
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
				usuario.setEmail(rs.getString(COL_NOMBRE));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setPassword(rs.getString(COL_PASSWORD));
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
			log.info("::::[update]::::Valor ____________________ 1::::Nombre:::Value:::" + usuario.getEmail() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[update]::::Valor ____________________ 2::::Password:::Value:::" + usuario.getPassword() + "::::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[update]::::Valor ____________________ 3::::Rol:::Value:::" + usuario.getRol().getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, usuario.getCuentaActiva());
			log.info("::::[update]::::Valor ____________________ 4::::cuentaActiva:::Value:::" + usuario.getCuentaActiva() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(5, usuario.getEmail());
			log.info("::::[update]::::Valor ____________________ 5::::usuario:::Value:::" + usuario.getEmail() + "::::" + "Seteado CORRECTAMENTE:::");
			
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
	
	

}
