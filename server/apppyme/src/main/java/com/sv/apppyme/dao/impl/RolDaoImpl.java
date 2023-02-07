package com.sv.apppyme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dao.RolDao;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.utils.Constantes;

public class RolDaoImpl implements RolDao {

	@Override
	public GenericEntityResponse<List<Rol>> getAll() {
		Rol rol;
		List<Rol> ls = new ArrayList<>();
		GenericEntityResponse<List<Rol>> resEntity = new GenericEntityResponse<>();
		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		try {
			con = ConexionPostgres.getConnecion();
			stmt = ConexionPostgres.getPreparedStatement(con, Constantes.DB_TABLA_ROL_QUERY_SELECT_EVERYTHING);
			rs = ConexionPostgres.executeQuery(stmt);
			while (rs.next()) {
				rol = new Rol();
				rol.setId(rs.getInt(Constantes.DB_TABLA_ROL_COL_ID));
				rol.setDescripcion(Constantes.DB_TABLA_ROL_COL_DESCRIPCION);
				ls.add(rol);
			}
			rs.close();
			stmt.close();
			con.close();
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setEntity(ls);
		} catch (SQLException e) {
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(Constantes.FAIL);
			e.printStackTrace();
		} catch (Exception e) {
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(Constantes.FAIL);
			e.printStackTrace();
		}

		return null;
	}

}
