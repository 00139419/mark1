package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.repository.IUsuario;
import com.sv.apppyme.utils.Constantes;

@Service
public class UsuarioDao implements IUsuario {
	
	Logger log = Logger.getLogger(UsuarioDao.class);
	
	//nombre de la tabla
	public static final String DB_TABLA_USUARIO = "usuario";
	
	//columnas de la tabla usuario
	public static final String COL_ID = "id";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	public static final String COL_ROL_ID = "rol_id";
	public static final String COL_CUENTA_ACTIVA = "cuentaactiva";
	
	//consultas de la tabla usuario
	public static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM " + DB_TABLA_USUARIO + " WHERE " + COL_USERNAME +" = ?";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_USUARIO + " WHERE " +  COL_ID + "= ?";
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_USUARIO + " (" +  COL_USERNAME +  ", " + COL_PASSWORD +", " + COL_ROL_ID + ", " + COL_CUENTA_ACTIVA + ") VALUES (?,?,?,?)";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_USUARIO + " SET " +  COL_USERNAME +  "= ?, "  +  COL_PASSWORD +  "= ?, "  +  COL_ROL_ID +  "= ?, "  +  COL_CUENTA_ACTIVA +  "= ?" ;
	
	
	@Override
	public SuperGenericResponse insertar(Usuario usuario) {
		log.info("::::[Incio]::::[insertar]::::Iniciando implementacion del DAO para los usuarios::::");
		SuperGenericResponse resDao = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insertar]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insertar]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insertar]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, usuario.getUsername());
			log.info("::::[insertar]::::Valor -> 1::::Nombre:::Value:::" + usuario.getUsername() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[insertar]::::Valor -> 2::::Password:::Value:::" + usuario.getPassword() + ":::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[insertar]::::Valor -> 3::::Rol:::Value:::" + usuario.getRol().getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, false);
			log.info("::::[insertar]::::Valor -> 4::::cuentaActiva:::Value:::" + false + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[insertar]:::SQL generado:::" + stmt.toString() + "::::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[insertar]::::stmt ejecutado correctamente::::");
			log.info("::::[insertar]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.OK);
				log.info("::::[insertar]::::Se crearon " + resultado + " nuevos usuarios\"::::");
				log.info("::::[insertar]::::Enviando repsuesta del implementacion del DAO::::");
			}
			log.info("::::[insertar]::::Fin interpretando Data recibida::::");
			stmt.close();
			log.info("::::[insertar]::::PreparedStatement CERRADO correctamente::::");
			con.close();
			log.info("::::[insertar]::::Conexion CERRADO correctamente::::");
			log.info("::::[insertar]::::Enviando repsuesta del implementacion del DAO::::");
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[insertar]::::Error de SQL en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[insertar]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insertar]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insertar]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[insertar]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insertar]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[insertar]::::Fin implementacion del DAO para los roles::::");
		return resDao;
	}

	@Override
	public GenericEntityResponse<Usuario> selectByUsername(String username) {
		log.info("::::[Incio]::::[selectByUsername]::::Iniciando implementacion del DAO para los usuarios::::");
		GenericEntityResponse<Usuario> resEntity = new GenericEntityResponse<>();
		try {
			Usuario usuario = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[selectByUsername]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_USERNAME);
			log.info("::::[selectByUsername]::::PreparedStatment CREADO correctamente::::");
			stmt.setString(1, username);
			log.info("::::[selectByUsername]::::Valor -> 1::::Username:::Value:::" + username + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[selectByUsername]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[selectByUsername]::::ResultSet CREADO correctamente::::");
			log.info("::::[selectByUsername]::::Interpretando Data recibida::::");
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt(COL_ID));
				usuario.setUsername(rs.getString(COL_USERNAME));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setPassword(rs.getString(COL_PASSWORD));
			}
			log.info("::::[selectByUsername]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[selectByUsername]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[selectByUsername]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[selectByUsername]::::Conexion CERRADO correctamente::::");
			log.info("::::[selectByUsername]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setEntity(usuario);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[selectByUsername]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[selectByUsername]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		}
		return resEntity;
	}

	@Override
	public GenericEntityResponse<Usuario> selectById(int id) {
		log.info("::::[Incio]::::[selectById]::::Iniciando implementacion del DAO para los usuarios::::");
		GenericEntityResponse<Usuario> resEntity = new GenericEntityResponse<>();
		try {
			Usuario usuario = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[selectById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_ID);
			log.info("::::[selectById]::::PreparedStatment CREADO correctamente::::");
			stmt.setInt(1, id);
			log.info("::::[selectById]::::Valor -> 1::::id:::Value:::" + id + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[selectById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[selectById]::::ResultSet CREADO correctamente::::");
			log.info("::::[selectById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt(COL_ID));
				usuario.setUsername(rs.getString(COL_USERNAME));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setPassword(rs.getString(COL_PASSWORD));
			}
			log.info("::::[selectById]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[selectById]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[selectById]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[selectById]::::Conexion CERRADO correctamente::::");
			log.info("::::[selectById]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setEntity(usuario);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[selectById]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[selectById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[selectById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[selectById]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[selectById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[selectById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		}
		return resEntity;
	}

	@Override
	public SuperGenericResponse update(Usuario usuario) {
		log.info("::::[Incio]::::[update]::::Iniciando implementacion del DAO para los usuarios::::");
		SuperGenericResponse resDao = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_UPDATE);
			log.info("::::[update]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[update]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, usuario.getUsername());
			log.info("::::[update]::::Valor -> 1::::Nombre:::Value:::" + usuario.getUsername() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[update]::::Valor -> 2::::Password:::Value:::" + usuario.getPassword() + "::::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[update]::::Valor -> 3::::Rol:::Value:::" + usuario.getRol().getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, usuario.getCuentaActiva());
			log.info("::::[update]::::Valor -> 4::::cuentaActiva:::Value:::" + usuario.getCuentaActiva() + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[update]:::SQL generado:::" + stmt.toString() + "::::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[update]::::stmt ejecutado correctamente::::");
			log.info("::::[update]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.OK);
				log.info("::::[update]::::Se crearon " + resultado + " nuevos usuarios\"::::");
				log.info("::::[update]::::Enviando repsuesta del implementacion del DAO::::");
			}
			log.info("::::[update]::::Fin interpretando Data recibida::::");
			stmt.close();
			log.info("::::[update]::::PreparedStatement CERRADO correctamente::::");
			con.close();
			log.info("::::[update]::::Conexion CERRADO correctamente::::");
			log.info("::::[update]::::Enviando repsuesta del implementacion del DAO::::");
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[update]::::Error de SQL en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[update]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[update]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		} catch (Exception e) {
			log.info("::::[ERROR]::::[update]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[update]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[update]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[update]::::Fin implementacion del DAO para los roles::::");
		return resDao;
	}
	
	

}
