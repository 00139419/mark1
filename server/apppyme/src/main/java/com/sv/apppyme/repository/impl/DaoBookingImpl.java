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
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Booking;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.repository.IRepoBooking;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.Log4jUtils;

@Repository
public class DaoBookingImpl implements IRepoBooking {
	
	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_BOOKING = "booking";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_LISTA_DE_PROD = "listp";
	public static final String COL_FECHA = "fecha";
	public static final String COL_VIGENTE = "vigente";
	public static final String COL_PAGADA = "pagada";
	public static final String COL_CANCELADA = "cancelada";
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_BOOKING
			+ "(" + COL_USER_ID + ","
			+ COL_LISTA_DE_PROD + ","
			+ COL_FECHA + ","
			+ COL_VIGENTE + ","
			+ COL_PAGADA + ","
			+ COL_CANCELADA + ")"
			+ " VALUES (?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_BOOKING;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_BOOKING + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_BOOKING 
			+ " SET " 
				+ COL_USER_ID + " = ?, "
				+ COL_LISTA_DE_PROD + " = ?, "
				+ COL_FECHA + " = ?, "
				+ COL_VIGENTE + " = ?, "
				+ COL_PAGADA + " = ?, "
				+ COL_CANCELADA + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_BOOKING + " WHERE " + COL_ID + " = ?";
	
	@Override
	public SuperGenericResponse insert(Booking booking) {
		log.info("::::[Incio]::::[insert]::::Iniciando implementacion del DAO para los usuarios::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insert]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insert]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setInt(1, booking.getUser().getId());
			log.info("::::[insert]::::Valor ____________________ 1::::Usuario ID:::Value:::" + booking.getUser().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, booking.getListP());
			log.info("::::[insert]::::Valor ____________________ 2::::Lista de productos:::Value:::" + booking.getListP() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(3, DateUtils.convertDateToTimeStamp(booking.getFecha()));
			log.info("::::[insert]::::Valor ____________________ 3::::Fecha:::Value:::" + DateUtils.convertDateToTimeStamp(booking.getFecha()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, booking.isVigente());
			log.info("::::[insert]::::Valor ____________________ 4::::Esta vigente?:::Value:::" + booking.isVigente() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(5, booking.isPagada());
			log.info("::::[insert]::::Valor ____________________ 5::::Esta Pagada?:::Value:::" + booking.isPagada() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(6, booking.isCancelada());
			log.info("::::[insert]::::Valor ____________________ 6::::Esta cancelada?:::Value:::" + booking.isCancelada() + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[insert]:::SQL generado:::" + stmt.toString() + "::::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[insert]::::stmt ejecutado correctamente::::");
			log.info("::::[insert]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				res.setCodigo(Constantes.SUCCES);
				res.setMensaje(Constantes.OK);
				log.info("::::[insert]::::Se crearon " + resultado + " nuevos registros::::");
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
	public SuperGenericResponse update(Booking booking) {
		log.info("::::[Incio]::::[update]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_UPDATE);
			log.info("::::[update]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[update]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setInt(1, booking.getUser().getId());
			log.info("::::[update]::::Valor ____________________ 1::::Usuario ID:::Value:::" + booking.getUser().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, booking.getListP());
			log.info("::::[update]::::Valor ____________________ 2::::Lista de productos:::Value:::" + booking.getListP() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(3, DateUtils.convertDateToTimeStamp(booking.getFecha()));
			log.info("::::[update]::::Valor ____________________ 3::::Fecha:::Value:::" + DateUtils.convertDateToTimeStamp(booking.getFecha()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(4, booking.isVigente());
			log.info("::::[update]::::Valor ____________________ 4::::Esta vigente?:::Value:::" + booking.isVigente() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(5, booking.isPagada());
			log.info("::::[update]::::Valor ____________________ 5::::Esta Pagada?:::Value:::" + booking.isPagada() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setBoolean(6, booking.isCancelada());
			log.info("::::[update]::::Valor ____________________ 6::::Esta cancelada?:::Value:::" + booking.isCancelada() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(7, booking.getId());
			log.info("::::[update]::::Valor ____________________ 6::::ID:::Value:::" + booking.getId() + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse delete(Booking booking) {
		log.info("::::[INCIO]::::[delete]::::Incio de DAO::::");
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[delete]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_DELETE);
			log.info("::::[delete]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, booking.getId());
			log.info("::::[delete]::::Valor ____________________ 1::::id:::Value:::" + booking.getId() + "Seteado CORRECTAMENTE::::");
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
	@Override
	public GenericEntityResponse<Booking> getOneById(int id) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Booking> res = new GenericEntityResponse<>();
		try {
			Booking booking = new Booking();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_ID);
			log.info("::::[getOneById]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getOneById]::::Seteando datos al PreparedStatment::::");
			stmt.setInt(1, id);
			log.info("::::[getOneById]::::Valor ____________________ 1::::Id:::Value:::" + id + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				booking.setId(rs.getInt(COL_ID));
				booking.setUser(new User(rs.getInt(COL_USER_ID)));
				booking.setListP(rs.getString(COL_LISTA_DE_PROD));
				booking.setFecha(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA)));
				booking.setVigente(rs.getBoolean(COL_VIGENTE));
				booking.setPagada(rs.getBoolean(COL_PAGADA));
				booking.setCancelada(rs.getBoolean(COL_CANCELADA));
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
			res.setEntity(booking);
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
	@Override
	public GenericEntityResponse<List<Booking>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		Booking booking;
		List<Booking> ls = new ArrayList<>();
		GenericEntityResponse<List<Booking>> res = new GenericEntityResponse<>();
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
				booking = new Booking();
				booking.setId(rs.getInt(COL_ID));
				booking.setUser(new User(rs.getInt(COL_USER_ID)));
				booking.setListP(rs.getString(COL_LISTA_DE_PROD));
				booking.setFecha(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA)));
				booking.setVigente(rs.getBoolean(COL_VIGENTE));
				booking.setPagada(rs.getBoolean(COL_PAGADA));
				booking.setCancelada(rs.getBoolean(COL_CANCELADA));
				ls.add(booking);
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
	
}
