package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.repository.IRepoRol;
import com.sv.apppyme.utils.Constantes;

@Repository
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
		GenericEntityResponse<List<Rol>> res = new GenericEntityResponse<>();
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
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			res.setListaEntity(ls);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getAll]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getAll]::::Error de generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		log.info("::::[FIN]::::[getAll]::::Fin implementacion del DAO::::");
		return res;
	}

	@Override
	public GenericEntityResponse<Rol> getOneByDescripcition(String descripcion) {
		log.info("::::[Incio]::::[getOneByDescripcition]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Rol> res = new GenericEntityResponse<>();
		try {
			Rol rol = new Rol();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneByDescripcition]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_DESCRIPCION);
			log.info("::::[getOneByDescripcition]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getOneByDescripcition]::::Seteando datos al PreparedStatment::::");
			log.info("::::[getOneByDescripcition]:::SQL generado:::" + stmt.toString() + "::::");
			stmt.setString(1, descripcion);
			log.info("::::[getOneByDescripcition]::::Valor ____________________ 1::::descripcion:::Value:::" + descripcion + "Seteado CORRECTAMENTE:::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneByDescripcition]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneByDescripcition]::::Interpretando Data recibida::::");
			while(rs.next()) {
				rol.setId(rs.getInt(COL_ID));
				rol.setDescripcion(rs.getString(COL_DESCRIPCION));
			}
			log.info("::::[getOneByDescripcition]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getOneByDescripcition]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getOneByDescripcition]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[getOneByDescripcition]::::Conexion CERRADO correctamente::::");
			log.info("::::[getOneByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.SUCCES);
			res.setMensaje(Constantes.OK);
			res.setEntity(rol);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getRolByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Error de generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getOneByDescripcition]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}

	@Override
	public GenericEntityResponse<Rol> getOneById(int id) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Rol> res = new GenericEntityResponse<>();
		try {
			Rol rol = new Rol();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_ID);
			log.info("::::[getOneById]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getOneById]::::Seteando datos al PreparedStatment::::");
			stmt.setInt(1, id);
			log.info("::::[getOneById]::::Valor ____________________ 1::::descripcion:::Value:::" + id + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				rol.setId(rs.getInt(COL_ID));
				rol.setDescripcion(rs.getString(COL_DESCRIPCION));
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
			res.setEntity(rol);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[getOneById]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getOneById]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getOneById]::::Error de generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[getOneById]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getOneById]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getOneById]::::Enviando repsuesta del implementacion del DAO::::");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}

}
