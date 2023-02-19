package com.sv.apppyme.conexciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sv.apppyme.utils.Constantes;

public class ConexionPostgres {

	private static final String URL = Constantes.JDBC_URL + Constantes.DB_PUERTO + Constantes.DB_NAME;
	
	public static Connection getConnecion() throws SQLException {
		Connection con = DriverManager.getConnection(URL, Constantes.DB_USERNAME, Constantes.DB_PASSWORD);
		return con;
	}
	
	public static PreparedStatement getPreparedStatement(Connection con, String query) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(query);
		return stmt;
	}
	
	public static ResultSet executeQuery(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public static int updateQuery(PreparedStatement stmt) throws SQLException {
		int rs = stmt.executeUpdate();
		return rs;
	}
}
	