package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.repository.IRepoRol;
import com.sv.apppyme.utils.Constantes;

@Service
public class RolDao implements IRepoRol {
	
	//nombre de la tabla
	public static final String DB_TABLA_ROL = "rol";
	
	//columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_DESCRIPCION = "descripcion";
	

	Logger log = Logger.getLogger(RolDao.class);
	
	//querys de la tabla
	private String SQL_SELECT_BY_DESCRIPCION = "SELECT * FROM " + DB_TABLA_ROL + " WHERE " + COL_DESCRIPCION + " = ?";
	private String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_ROL + " WHERE " + COL_ID + " = ?";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_ROL;

	@Override
	public GenericEntityResponse<List<Rol>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		Rol rol;
		List<Rol> ls = new ArrayList<>();
		GenericEntityResponse<List<Rol>> resEntity = new GenericEntityResponse<>();
		Connection conn;
		PreparedStatement stmt;
		ResultSet rs;
		try {
			conn = ConexionPostgres.getConnecion();
			log.info("::::[getAll]::::Conexion CREADO correctamente::::");
			stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT);
			log.info("::::[getAll]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getAll]:::SQL generado:::" + stmt.toString() + "::::");
			rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getAll]::::ResultSet CREADO correctamente::::");
			log.info("::::[getAll]::::Interpretando Data recibida::::");
			while (rs.next()) {
				rol = new Rol();
				rol.setId(rs.getInt(COL_ID));
				rol.setDescripcion(rs.getString(COL_DESCRIPCION));
				ls.add(rol);
			}
			log.info("::::[getAll]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getAll]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getAll]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getAll]::::Conexion CERRADO correctamente::::");
			log.info("::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setListaEntity(ls);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getAll]::::Error de SQL en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getAll]::::Error de generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		}
		log.info("::::[FIN]::::[getAll]::::Fin implementacion del DAO para los roles::::");
		return resEntity;
	}

	@Override
	public GenericEntityResponse<Rol> getRolByDescripcition(String descripcion) {
		log.info("::::[Incio]::::[getRolByDescripcition]::::Iniciando implementacion del DAO para los roles::::");
		GenericEntityResponse<Rol> resEntity = new GenericEntityResponse<>();
		try {
			Rol rol = new Rol();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getRolByDescripcition]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_DESCRIPCION);
			log.info("::::[getRolByDescripcition]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getRolByDescripcition]::::Seteando datos al PreparedStatment::::");
			log.info("::::[getRolById]:::SQL generado:::" + stmt.toString() + "::::");
			stmt.setString(1, descripcion);
			log.info("::::[getRolByDescripcition]::::Valor -> 1::::descripcion:::Value:::" + descripcion + "Seteado CORRECTAMENTE:::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getRolByDescripcition]::::ResultSet CREADO correctamente::::");
			log.info("::::[getRolByDescripcition]::::Interpretando Data recibida::::");
			while(rs.next()) {
				rol.setId(rs.getInt(COL_ID));
				rol.setDescripcion(rs.getString(COL_DESCRIPCION));
			}
			log.info("::::[getRolByDescripcition]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getRolByDescripcition]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getRolById]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getRolByDescripcition]::::Conexion CERRADO correctamente::::");
			log.info("::::[getRolByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setEntity(rol);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Error de SQL en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Error de generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		}
		return resEntity;
	}

	@Override
	public GenericEntityResponse<Rol> getRolById(int id) {
		log.info("::::[Incio]::::[getRolById]::::Iniciando implementacion del DAO para los roles::::");
		GenericEntityResponse<Rol> resEntity = new GenericEntityResponse<>();
		try {
			Rol rol = new Rol();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getRolByDescripcition]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_ID);
			log.info("::::[getRolById]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getRolById]::::Seteando datos al PreparedStatment::::");
			stmt.setInt(1, id);
			log.info("::::[getRolById]::::Valor -> 1::::descripcion:::Value:::" + id + "Seteado CORRECTAMENTE:::");
			log.info("::::[getRolById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getRolById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getRolById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				rol.setId(rs.getInt(COL_ID));
				rol.setDescripcion(rs.getString(COL_DESCRIPCION));
			}
			log.info("::::[getRolById]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getRolById]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getRolById]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getRolById]::::Conexion CERRADO correctamente::::");
			log.info("::::[getRolById]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setEntity(rol);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getRolById]::::Error de SQL en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getRolById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getRolById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getRolById]::::Error de generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getRolById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getRolById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getRolById]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		}
		return resEntity;
	}

}
