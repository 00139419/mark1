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
import com.sv.apppyme.entities.CabeceraFac;
import com.sv.apppyme.entities.Compra;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.User;
import com.sv.apppyme.repository.IRepoCabeceraFactura;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.Log4jUtils;

@Repository
public class DaoCabeceraFacImpl implements IRepoCabeceraFactura{

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_CABECERA_FACTURA = "cabecerafac";
	
	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_COMPRA_ID = "compra_id";
	public static final String COL_IMG_ID = "img_id";
	public static final String COL_TIPO_DOCUMENTO = "tipodoc";
	public static final String COL_NUMERO_DOCUMENTO = "numdoc";
	public static final String COL_FECHA = "fecha";
	public static final String COL_METODO_PAGO = "metpag";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_TOTAL = "total";
	
	
	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_CABECERA_FACTURA
			+ "(" + COL_USER_ID + ","
			+ COL_COMPRA_ID + ","
			+ COL_IMG_ID + ","
			+ COL_TIPO_DOCUMENTO + ","
			+ COL_NUMERO_DOCUMENTO + ","
			+ COL_FECHA + ","
			+ COL_METODO_PAGO + ","
			+ COL_NOMBRE + ","
			+ COL_TOTAL + ")"
			+ " VALUES (?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_CABECERA_FACTURA;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_CABECERA_FACTURA + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_CABECERA_FACTURA 
			+ " SET " 
				+ COL_USER_ID + " = ?, "
				+ COL_COMPRA_ID + " = ?, "
				+ COL_IMG_ID + " = ?, "
				+ COL_TIPO_DOCUMENTO + " = ?, "
				+ COL_NUMERO_DOCUMENTO + " = ?, "
				+ COL_FECHA + " = ?, "
				+ COL_METODO_PAGO + " = ?, "
				+ COL_NOMBRE + " = ?, "
				+ COL_TOTAL + " = ? "
			+ " WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_CABECERA_FACTURA + " WHERE " + COL_ID + " = ?";
	@Override
	public SuperGenericResponse insert(CabeceraFac cabeceraFactura) {
		log.info("::::[Incio]::::[insert]::::Iniciando implementacion del DAO para los usuarios::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insert]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insert]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setInt(1, cabeceraFactura.getUser().getId());
			log.info("::::[insert]::::Valor ____________________ 1::::Usuario ID:::Value:::" + cabeceraFactura.getUser().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(2, cabeceraFactura.getCompra().getId());
			log.info("::::[insert]::::Valor ____________________ 2::::ID de compra:::Value:::" + cabeceraFactura.getCompra().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, cabeceraFactura.getImg().getId());
			log.info("::::[insert]::::Valor ____________________ 3::::ID de img:::Value:::" + cabeceraFactura.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(4, cabeceraFactura.getTipoDoc());
			log.info("::::[insert]::::Valor ____________________ 4::::Tipo de documento:::Value:::" + cabeceraFactura.getTipoDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(5, cabeceraFactura.getNumDoc());
			log.info("::::[insert]::::Valor ____________________ 5::::Numero de documento:::Value:::" + cabeceraFactura.getNumDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(6, DateUtils.convertDateToTimeStamp(cabeceraFactura.getFecha()));
			log.info("::::[insert]::::Valor ____________________ 6::::Fecha:::Value:::" + DateUtils.convertDateToTimeStamp(cabeceraFactura.getFecha()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(7, cabeceraFactura.getMetPag());
			log.info("::::[insert]::::Valor ____________________ 7::::Metodo de pago:::Value:::" +cabeceraFactura.getMetPag() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(8, cabeceraFactura.getNombre());
			log.info("::::[insert]::::Valor ____________________ 8::::Nombre:::Value:::" + cabeceraFactura.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setFloat(9, cabeceraFactura.getTotal());
			log.info("::::[insert]::::Valor ____________________ 9::::Total:::Value:::" + cabeceraFactura.getTotal() + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse update(CabeceraFac cabeceraFactura) {
		log.info("::::[Incio]::::[update]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_UPDATE);
			log.info("::::[update]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[update]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setInt(1, cabeceraFactura.getUser().getId());
			log.info("::::[update]::::Valor ____________________ 1::::Usuario ID:::Value:::" + cabeceraFactura.getUser().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(2, cabeceraFactura.getCompra().getId());
			log.info("::::[update]::::Valor ____________________ 2::::ID de compra:::Value:::" + cabeceraFactura.getCompra().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, cabeceraFactura.getImg().getId());
			log.info("::::[update]::::Valor ____________________ 3::::ID de img:::Value:::" + cabeceraFactura.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(4, cabeceraFactura.getTipoDoc());
			log.info("::::[update]::::Valor ____________________ 4::::Tipo de documento:::Value:::" + cabeceraFactura.getTipoDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(5, cabeceraFactura.getNumDoc());
			log.info("::::[update]::::Valor ____________________ 5::::Numero de documento:::Value:::" + cabeceraFactura.getNumDoc() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(6, DateUtils.convertDateToTimeStamp(cabeceraFactura.getFecha()));
			log.info("::::[update]::::Valor ____________________ 6::::Fecha:::Value:::" + DateUtils.convertDateToTimeStamp(cabeceraFactura.getFecha()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(7, cabeceraFactura.getMetPag());
			log.info("::::[update]::::Valor ____________________ 7::::Metodo de pago:::Value:::" +cabeceraFactura.getMetPag() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(8, cabeceraFactura.getNombre());
			log.info("::::[update]::::Valor ____________________ 8::::Nombre:::Value:::" + cabeceraFactura.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setFloat(9, cabeceraFactura.getTotal());
			log.info("::::[update]::::Valor ____________________ 9::::Total:::Value:::" + cabeceraFactura.getTotal() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setFloat(10, cabeceraFactura.getId());
			log.info("::::[update]::::Valor ____________________ 10::::ID:::Value:::" + cabeceraFactura.getId() + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse delete(CabeceraFac cabeceraFactura) {
		log.info("::::[INCIO]::::[delete]::::Incio de DAO::::");
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[delete]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_DELETE);
			log.info("::::[delete]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, cabeceraFactura.getId());
			log.info("::::[delete]::::Valor ____________________ 1::::id:::Value:::" + cabeceraFactura.getId() + "Seteado CORRECTAMENTE::::");
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
	public GenericEntityResponse<CabeceraFac> getOneById(int id) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<CabeceraFac> res = new GenericEntityResponse<>();
		try {
			CabeceraFac cabeceraFactura = new CabeceraFac();
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
				cabeceraFactura.setId(rs.getInt(COL_ID));
				cabeceraFactura.setUser(new User(rs.getInt(COL_USER_ID)));
				cabeceraFactura.setCompra(new Compra(rs.getInt(COL_COMPRA_ID)));
				cabeceraFactura.setImg(new Img(rs.getInt(COL_IMG_ID)));
				cabeceraFactura.setTipoDoc(rs.getString(COL_TIPO_DOCUMENTO));
				cabeceraFactura.setNumDoc(rs.getString(COL_NUMERO_DOCUMENTO));
				cabeceraFactura.setFecha(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA)));
				cabeceraFactura.setMetPag(rs.getString(COL_METODO_PAGO));
				cabeceraFactura.setNombre(rs.getString(COL_NOMBRE));
				cabeceraFactura.setTotal(rs.getFloat(COL_TOTAL));
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
			res.setEntity(cabeceraFactura);
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
	public GenericEntityResponse<List<CabeceraFac>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		CabeceraFac cabeceraFactura;
		List<CabeceraFac> ls = new ArrayList<>();
		GenericEntityResponse<List<CabeceraFac>> res = new GenericEntityResponse<>();
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
				cabeceraFactura = new CabeceraFac();
				cabeceraFactura.setId(rs.getInt(COL_ID));
				cabeceraFactura.setUser(new User(rs.getInt(COL_USER_ID)));
				cabeceraFactura.setCompra(new Compra(rs.getInt(COL_COMPRA_ID)));
				cabeceraFactura.setImg(new Img(rs.getInt(COL_IMG_ID)));
				cabeceraFactura.setTipoDoc(rs.getString(COL_TIPO_DOCUMENTO));
				cabeceraFactura.setNumDoc(rs.getString(COL_NUMERO_DOCUMENTO));
				cabeceraFactura.setFecha(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA)));
				cabeceraFactura.setMetPag(rs.getString(COL_METODO_PAGO));
				cabeceraFactura.setNombre(rs.getString(COL_NOMBRE));
				cabeceraFactura.setTotal(rs.getFloat(COL_TOTAL));
				ls.add(cabeceraFactura);
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
