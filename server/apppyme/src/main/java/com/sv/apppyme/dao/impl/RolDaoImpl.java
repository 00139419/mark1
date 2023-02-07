package com.sv.apppyme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dao.RolDao;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.utils.Constantes;

public class RolDaoImpl implements RolDao {

	Logger log = Logger.getLogger(RolDaoImpl.class);

	@Override
	public GenericEntityResponse<List<Rol>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		Rol rol;
		List<Rol> ls = new ArrayList<>();
		GenericEntityResponse<List<Rol>> resEntity = new GenericEntityResponse<>();
		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		try {
			con = ConexionPostgres.getConnecion();
			log.info("::::[getAll]::::Conexion CREADO correctamente::::");
			stmt = ConexionPostgres.getPreparedStatement(con, Constantes.DB_TABLA_ROL_QUERY_SELECT_EVERYTHING);
			log.info("::::[getAll]::::PreparedStatment CREADO correctamente::::");
			rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getAll]::::ResultSet CREADO correctamente::::");
			log.info("::::[getAll]::::Interpretando Data recibida::::");
			while (rs.next()) {
				rol = new Rol();
				rol.setId(rs.getInt(Constantes.DB_TABLA_ROL_COL_ID));
				rol.setDescripcion(Constantes.DB_TABLA_ROL_COL_DESCRIPCION);
				ls.add(rol);
			}
			log.info("::::[getAll]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[getAll]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[getAll]::::PreparedStatement CERRADO correctamente::::");
			con.close();
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
			resEntity.setMensaje(Constantes.FAIL);
		} catch (Exception e) {
			log.info("::::[ERROR]::::[getAll]::::Error de generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[getAll]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[getAll]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			log.info("::::[ERROR]::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[getAll]::::Fin implementacion del DAO para los roles::::");
		return resEntity;
	}

}
