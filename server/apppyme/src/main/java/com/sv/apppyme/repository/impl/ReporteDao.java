package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Reporte;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.repository.IRepoReporte;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.Log4jUtils;

@Repository
public class ReporteDao implements IRepoReporte{
	
	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_REPORTE = "reporte";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_BASE64 = "base64";
	public static final String COL_FECHA = "fecha";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_TIPODOC = "tipodoc";
	public static final String COL_NUMDOC = "numdoc";
	public static final String COL_TOTAL = "total";
	public static final String COL_TOTALREC = "totalrec";
	public static final String COL_TOTALCAM = "totalcam";
	public static final String COL_METPAG = "metpag";
	public static final String COL_LV = "lv";
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_REPORTE 
			+ "(" 
				+ COL_BASE64 + ", "
				+ COL_FECHA + ", "
				+ COL_NOMBRE + ", "
				+ COL_USER_ID + ", "
				+ COL_TIPODOC + ", "
				+ COL_NUMDOC + ", "
				+ COL_TOTAL + ", "
				+ COL_TOTALREC + ", "
				+ COL_TOTALCAM + ", "
				+ COL_METPAG + ", "
				+ COL_LV
			+ ")"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_REPORTE;
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_REPORTE + " WHERE " + COL_ID + " = ?";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_REPORTE + " WHERE " + COL_ID + " = ?";
	public static final String SQL_SELECT_BY_NUM_DOC = "SELECT * FROM " + DB_TABLA_REPORTE + " WHERE " + COL_NUMDOC + " = ?";
	public static final String SQL_SELECT_BY_FECHA = "SELECT * FROM " + DB_TABLA_REPORTE + " WHERE " + COL_FECHA + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_REPORTE
			+ " SET "
				+ COL_BASE64 + " = ?,"
				+ COL_FECHA + " = ?,"
				+ COL_NOMBRE + " = ?,"
				+ COL_USER_ID + " = ?,"
				+ COL_TIPODOC + " = ?,"
				+ COL_NUMDOC + " = ?,"
				+ COL_TOTAL + " = ?,"
				+ COL_TOTALREC + " = ?,"
				+ COL_TOTALCAM + " = ?,"
				+ COL_METPAG + " = ?,"
				+ COL_LV + " = ? "
			+ "WHERE " + COL_ID + " = ?";
	
	@Override
	public SuperGenericResponse insert(Reporte reporte) {
		log.info("::::[Incio]::::[insert]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insert]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insert]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, reporte.getBase64());
			log.info("::::[insert]::::Valor ____________________ 1::::base64:::Value:::" + reporte.getBase64() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(2, DateUtils.convertDateToTimeStamp(reporte.getFecha()));
			log.info("::::[insert]::::Valor ____________________ 2::::fecha:::Value:::" + DateUtils.convertDateToTimeStamp(reporte.getFecha()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(3, reporte.getNombre());
			log.info("::::[insert]::::Valor ____________________ 3::::nombre:::Value:::" + reporte.getBase64() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(4, reporte.getUserId().getId());
			log.info("::::[insert]::::Valor ____________________ 4::::user_id:::Value:::" + reporte.getUserId().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(5, reporte.getTipoDoc());
			log.info("::::[insert]::::Valor ____________________ 5::::tipo documento:::Value:::" + reporte.getTipoDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(6, reporte.getNumDoc());
			log.info("::::[insert]::::Valor ____________________ 6::::numero documento:::Value:::" + reporte.getNumDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(7, reporte.getTotal());
			log.info("::::[insert]::::Valor ____________________ 7::::total:::Value:::" + reporte.getTotal() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(8, reporte.getTotalrec());
			log.info("::::[insert]::::Valor ____________________ 8::::total dinero recibido:::Value:::" + reporte.getTotalrec() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(9, reporte.getTotalcam());
			log.info("::::[insert]::::Valor ____________________ 9::::total dinero cambio:::Value:::" + reporte.getTotalcam() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(10, reporte.getMetpag());
			log.info("::::[insert]::::Valor ____________________ 10::::metodo de pago:::Value:::" + reporte.getMetpag() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(11, reporte.getLv());
			log.info("::::[insert]::::Valor ____________________ 11::::lv:::Value:::" + reporte.getLv() + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse update(Reporte reporte) {
		log.info("::::[Incio]::::[update]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_UPDATE);
			log.info("::::[update]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[update]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, reporte.getBase64());
			log.info("::::[update]::::Valor ____________________ 1::::base64:::Value:::" + reporte.getBase64() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(2, DateUtils.convertDateToTimeStamp(reporte.getFecha()));
			log.info("::::[update]::::Valor ____________________ 2::::fecha:::Value:::" + DateUtils.convertDateToTimeStamp(reporte.getFecha()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(3, reporte.getNombre());
			log.info("::::[update]::::Valor ____________________ 3::::nombre:::Value:::" + reporte.getBase64() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(4, reporte.getUserId().getId());
			log.info("::::[update]::::Valor ____________________ 4::::user_id:::Value:::" + reporte.getUserId().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(5, reporte.getTipoDoc());
			log.info("::::[update]::::Valor ____________________ 5::::tipo documento:::Value:::" + reporte.getTipoDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(6, reporte.getNumDoc());
			log.info("::::[update]::::Valor ____________________ 6::::numero documento:::Value:::" + reporte.getNumDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(7, reporte.getTotal());
			log.info("::::[update]::::Valor ____________________ 7::::total:::Value:::" + reporte.getTotal() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(8, reporte.getTotalrec());
			log.info("::::[update]::::Valor ____________________ 8::::total dinero recibido:::Value:::" + reporte.getTotalrec() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(9, reporte.getTotalcam());
			log.info("::::[update]::::Valor ____________________ 9::::total dinero cambio:::Value:::" + reporte.getTotalcam() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(10, reporte.getMetpag());
			log.info("::::[update]::::Valor ____________________ 10::::metodo de pago:::Value:::" + reporte.getMetpag() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(11, reporte.getLv());
			log.info("::::[update]::::Valor ____________________ 11::::lv:::Value:::" + reporte.getLv() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(12, reporte.getId());
			log.info("::::[update]::::Valor ____________________ 12::::Id:::Value:::" + reporte.getId() + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse delete(Reporte reporte) {
		log.info("::::[INCIO]::::[delete]::::Incio de DAO::::");
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[delete]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_DELETE);
			log.info("::::[delete]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, reporte.getId());
			log.info("::::[delete]::::Valor ____________________ 1::::id:::Value:::" + reporte.getId() + "Seteado CORRECTAMENTE::::");
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
	public GenericEntityResponse<Reporte> getOneById(int id) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Reporte> res = new GenericEntityResponse<>();
		try {
			Reporte reporte = new Reporte();
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
				reporte.setId(rs.getInt(COL_ID));
				reporte.setBase64(rs.getString(COL_BASE64));
				reporte.setFecha(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA)));
				reporte.setNombre(rs.getString(COL_NOMBRE));
				reporte.setUserId(new Usuario(rs.getInt(COL_USER_ID)));
				reporte.setTipoDoc(rs.getString(COL_TIPODOC));
				reporte.setNumDoc(rs.getString(COL_NUMDOC));
				reporte.setTotal( (float) rs.getDouble(COL_TOTAL));
				reporte.setTotalrec( (float) rs.getDouble(COL_TOTALREC));
				reporte.setTotalcam( (float) rs.getDouble(COL_TOTALCAM));
				reporte.setMetpag(rs.getString(COL_METPAG));
				reporte.setLv(rs.getString(COL_LV));
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
			res.setEntity(reporte);
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
	public GenericEntityResponse<List<Reporte>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		Reporte reporte;
		List<Reporte> ls = new ArrayList<>();
		GenericEntityResponse<List<Reporte>> res = new GenericEntityResponse<>();
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
				reporte = new Reporte();
				reporte.setId(rs.getInt(COL_ID));
				reporte.setBase64(rs.getString(COL_BASE64));
				reporte.setFecha(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA)));
				reporte.setNombre(rs.getString(COL_NOMBRE));
				reporte.setUserId(new Usuario(rs.getInt(COL_USER_ID)));
				reporte.setTipoDoc(rs.getString(COL_TIPODOC));
				reporte.setNumDoc(rs.getString(COL_NUMDOC));
				reporte.setTotal( (float) rs.getDouble(COL_TOTAL));
				reporte.setTotalrec( (float) rs.getDouble(COL_TOTALREC));
				reporte.setTotalcam( (float) rs.getDouble(COL_TOTALCAM));
				reporte.setMetpag(rs.getString(COL_METPAG));
				reporte.setLv(rs.getString(COL_LV));
				ls.add(reporte);
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

	@Override
	public GenericEntityResponse<Reporte> getOneByNumDoc(String numdoc) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Reporte> res = new GenericEntityResponse<>();
		try {
			Reporte reporte = new Reporte();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_NUM_DOC);
			log.info("::::[getOneById]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getOneById]::::Seteando datos al PreparedStatment::::");
			stmt.setString(1, numdoc);
			log.info("::::[getOneById]::::Valor ____________________ 1::::Numero de documento:::Value:::" + numdoc + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				reporte.setId(rs.getInt(COL_ID));
				reporte.setBase64(rs.getString(COL_BASE64));
				reporte.setFecha(DateUtils.convetirStringToDate(rs.getString(COL_FECHA)));
				reporte.setNombre(rs.getString(COL_NOMBRE));
				reporte.setUserId(new Usuario(rs.getInt(COL_USER_ID)));
				reporte.setTipoDoc(rs.getString(COL_TIPODOC));
				reporte.setNumDoc(rs.getString(COL_NUMDOC));
				reporte.setTotal( (float) rs.getDouble(COL_TOTAL));
				reporte.setTotalrec( (float) rs.getDouble(COL_TOTALREC));
				reporte.setTotalcam( (float) rs.getDouble(COL_TOTALCAM));
				reporte.setMetpag(rs.getString(COL_METPAG));
				reporte.setLv(rs.getString(COL_LV));
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
			res.setEntity(reporte);
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
	public GenericEntityResponse<Reporte> getOneByFecha(Date fecha) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Reporte> res = new GenericEntityResponse<>();
		try {
			Reporte reporte = new Reporte();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_FECHA);
			log.info("::::[getOneById]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getOneById]::::Seteando datos al PreparedStatment::::");
			stmt.setTimestamp(1, DateUtils.convertDateToTimeStamp(fecha));
			log.info("::::[getOneById]::::Valor ____________________ 1::::fecha:::Value:::" + DateUtils.convertDateToTimeStamp(fecha) + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				reporte.setId(rs.getInt(COL_ID));
				reporte.setBase64(rs.getString(COL_BASE64));
				reporte.setFecha(DateUtils.convetirStringToDate(rs.getString(COL_FECHA)));
				reporte.setNombre(rs.getString(COL_NOMBRE));
				reporte.setUserId(new Usuario(rs.getInt(COL_USER_ID)));
				reporte.setTipoDoc(rs.getString(COL_TIPODOC));
				reporte.setNumDoc(rs.getString(COL_NUMDOC));
				reporte.setTotal( (float) rs.getDouble(COL_TOTAL));
				reporte.setTotalrec( (float) rs.getDouble(COL_TOTALREC));
				reporte.setTotalcam( (float) rs.getDouble(COL_TOTALCAM));
				reporte.setMetpag(rs.getString(COL_METPAG));
				reporte.setLv(rs.getString(COL_LV));
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
			res.setEntity(reporte);
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
