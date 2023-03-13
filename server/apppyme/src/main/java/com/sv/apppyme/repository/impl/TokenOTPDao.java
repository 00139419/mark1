package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.TokenOTP;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.repository.IRepoTokenOTP;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;

@Service
public class TokenOTPDao implements IRepoTokenOTP {

	Logger log = Logger.getLogger(getClass());

	// nombre de la tabla
	public static final String DB_TABLA_TOKENOPT = "tokenotp";

	// nombres de las columnas
	public static final String COL_ID = "id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_TOKEN = "token";
	public static final String COL_FECHA_CREACION = "fechadecreacion";
	public static final String COL_FECHA_VENCIMIENTO = "fechadevencimiento";
	public static final String COL_ES_VALIDO = "esvalido";
	public static final String COL_ESTA_VERIFICADO = "estaverificado";

	// consultas para la tabla
	public static final String SQL_SELECT_BY_TOKEN = "SELECT * FROM tokenotp WHERE token = ?";
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_TOKENOPT 
			+ " ( "
				+ COL_USER_ID + ", "
				+ COL_TOKEN + ", "
				+ COL_FECHA_CREACION + ", "
				+ COL_FECHA_VENCIMIENTO + ", "
				+ COL_ES_VALIDO + ", "
				+ COL_ESTA_VERIFICADO + " "
			+ ")"
			+ " values (?,?,?,?,?,?)";
	public static final String SQL_UPDATE = "UPDATE "  + DB_TABLA_TOKENOPT 
			+ " SET " 
				+ COL_USER_ID + "  = ?, " 
				+ COL_TOKEN + " = ?, " 
				+ COL_FECHA_CREACION + " = ?, " 
				+ COL_FECHA_VENCIMIENTO + " = ?, " 
				+ COL_ES_VALIDO + " = ?, " 
				+ COL_ESTA_VERIFICADO + " = ?"
			+ " WHERE " 
				+ COL_ID  + " = ?";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_TOKENOPT;
	public static final String SQL_DELETE = "DELETE FROM  " + DB_TABLA_TOKENOPT + " WHERE " + COL_ID + " = ?";
	
	@Override
	public GenericEntityResponse<TokenOTP> verificarTokenOTP(String numero) {
		log.info("::::[INCIO]::::[verificarTokenOTP]::::Incio de DAO TokenOTP::::");

		GenericEntityResponse<TokenOTP> resDao = new GenericEntityResponse<>();

		try {
			List<TokenOTP> ls = new ArrayList<>();
			TokenOTP tokenOtp = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[verificarTokenOTP]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_TOKEN);
			log.info("::::[verificarTokenOTP]::::PreparedStatment creado correctamente::::");
			stmt.setString(1, numero);
			log.info("::::[verificarTokenOTP]::::Valor ____________________ 1::::token:::Value:::" + numero
					+ "::::Seteado CORRECTAMENTE::::");
			log.info("::::[verificarTokenOTP]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[verificarTokenOTP]::::ResultSet creado correctamente::::");
			log.info("::::[verificarTokenOTP]::::InterpretandoData::::");
			while (rs.next()) {
				tokenOtp = new TokenOTP();
				tokenOtp.setId(rs.getInt(COL_ID));
				tokenOtp.setToken(rs.getString(COL_TOKEN));
				tokenOtp.setEsValido(rs.getBoolean(COL_ES_VALIDO));
				tokenOtp.setFechaDeCreacion(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_CREACION)));
				tokenOtp.setFechaDeVencimiento(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_VENCIMIENTO)));
				tokenOtp.setEstaVerificado(rs.getBoolean(COL_ESTA_VERIFICADO));
				tokenOtp.setUsuario(new Usuario(rs.getInt(COL_USER_ID)));
				
				ls.add(tokenOtp);
			}
			resDao.setListaEntity(ls);
			log.info("::::[verificarTokenOTP]::::Data interpretada correctamente::::");
			log.info("::::[verificarTokenOTP]::::cantidad de campos encontrados con el mismo token::::" + ls.size()
					+ "::::");
			if (ls.isEmpty()) {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.OK);
			} else {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.FAIL);
			}
			log.info("::::[verificarTokenOTP]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info(
					"::::[ERROR]::::[verificarTokenOTP]::::Error generico en la verificacion si existe ese tokenOTP::::");
			log.info("::::[ERROR]::::[verificarTokenOTP]::::Mensaje::::" + e.getMessage() + "::::");
			log.info(e.getStackTrace().toString());
			e.printStackTrace();
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}
		log.info("::::[verificarTokenOTP]::::Retornando respuesta del DAO::::");
		return resDao;
	}

	@Override
	public SuperGenericResponse insert(TokenOTP tokenOTP) {
		log.info("::::[INCIO]::::[insert]::::Incio de DAO TokenOTP::::");
		SuperGenericResponse resDao = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_INSERT);

			log.info("::::[insert]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, tokenOTP.getUsuario().getId());
			log.info("::::[insert]::::Valor ____________________ 1::::token:::Value:::" + tokenOTP.getUsuario().getId()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setString(2, tokenOTP.getToken());
			log.info("::::[insert]::::Valor ____________________ 2::::token:::Value:::" + tokenOTP.getToken()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setDate(3, DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeCreacion()));
			log.info("::::[insert]::::Valor ____________________ 3::::token:::Value:::"
					+ DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeCreacion())
					+ "Seteado CORRECTAMENTE::::");
			stmt.setDate(4, DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeVencimiento()));
			log.info("::::[insert]::::Valor ____________________ 4::::token:::Value:::"
					+ DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeVencimiento())
					+ "Seteado CORRECTAMENTE::::");
			stmt.setBoolean(5, tokenOTP.getEsValido());
			log.info("::::[insert]::::Valor ____________________ 5::::token:::Value:::" + tokenOTP.getEsValido()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setBoolean(6, tokenOTP.getEstaVerificado());
			log.info("::::[insert]::::Valor ____________________ 6::::token:::Value:::" + tokenOTP.getEstaVerificado()
					+ "Seteado CORRECTAMENTE::::");
			log.info("::::[insert]:::SQL generado:::" + stmt.toString() + "::::");

			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[insert]::::Datos guardado correctamente::::");
			log.info("::::[insert]::::Cantidad de datos guardados::::value::::" + resultado + "::::");
			resDao.setCodigo(Constantes.SUCCES);
			resDao.setMensaje(Constantes.OK);
			log.info("::::[insert]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insert]::::Error generico en insertar nuevo token::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			e.printStackTrace();
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}

		log.info("::::[FIN]::::[insertToken]::::Fin de DAO TokenOTP::::");
		return resDao;
	}

	@Override
	public SuperGenericResponse update(TokenOTP tokenOTP) {
		log.info("::::[INCIO]::::[update]::::Incio de DAO TokenOTP::::");
		SuperGenericResponse resDao = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_UPDATE);

			log.info("::::[update]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, tokenOTP.getUsuario().getId());
			log.info("::::[update]::::Valor ____________________ 1::::token:::Value:::" + tokenOTP.getUsuario().getId()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setString(2, tokenOTP.getToken());
			log.info("::::[update]::::Valor ____________________ 2::::token:::Value:::" + tokenOTP.getToken()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setDate(3, DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeCreacion()));
			log.info("::::[numeroExiste]::::____________________ 3::::token:::Value:::"
					+ DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeCreacion())
					+ "Seteado CORRECTAMENTE::::");
			stmt.setDate(4, DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeVencimiento()));
			log.info("::::[update]::::Valor ____________________ 4::::token:::Value:::"
					+ DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeVencimiento())
					+ "Seteado CORRECTAMENTE::::");
			stmt.setBoolean(5, tokenOTP.getEsValido());
			log.info("::::[update]::::Valor ____________________ 5::::token:::Value:::" + tokenOTP.getEsValido()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setBoolean(6, tokenOTP.getEstaVerificado());
			log.info("::::[update]::::Valor ____________________ 6::::token:::Value:::" + tokenOTP.getEstaVerificado()
					+ "Seteado CORRECTAMENTE::::");
			stmt.setInt(7, tokenOTP.getId());
			log.info("::::[update]::::Valor ____________________ 7::::ID:::Value:::" + tokenOTP.getId()
					+ "Seteado CORRECTAMENTE::::");log.info("::::[update]:::SQL generado:::" + stmt.toString() + "::::");

			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[update]::::Datos guardado correctamente::::");
			log.info("::::[update]::::Cantidad de datos guardados::::value::::" + resultado + "::::");
			resDao.setCodigo(Constantes.SUCCES);
			resDao.setMensaje(Constantes.OK);
			log.info("::::[update]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[update]::::Error generico en insertar nuevo token::::");
			log.info("::::[ERROR]::::[update]::::Mensaje::::" + e.getMessage() + "::::");
			e.printStackTrace();
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}

		log.info("::::[FIN]::::[update]::::Fin de DAO TokenOTP::::");
		return resDao;
	}

	@Override
	public GenericEntityResponse<List<TokenOTP>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		TokenOTP token;
		List<TokenOTP> ls = new ArrayList<>();
		GenericEntityResponse<List<TokenOTP>> resEntity = new GenericEntityResponse<>();
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
				token = new TokenOTP();
				token.setId(rs.getInt(COL_ID));
				token.setUsuario(new Usuario(rs.getInt(COL_USER_ID)));
				token.setEstaVerificado(rs.getBoolean(COL_ESTA_VERIFICADO));
				token.setFechaDeCreacion(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_CREACION)));
				token.setFechaDeVencimiento(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_VENCIMIENTO)));
				token.setEsValido(rs.getBoolean(COL_ES_VALIDO));
				token.setToken(rs.getString(COL_TOKEN));
				ls.add(token);
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
			resEntity.setEntity(ls);
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
	public SuperGenericResponse delete(TokenOTP tokenOTP) {
		log.info("::::[INCIO]::::[delete]::::Incio de DAO TokenOTP::::");
		SuperGenericResponse resDao = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[delete]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_DELETE);

			log.info("::::[delete]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, tokenOTP.getId());
			log.info("::::[delete]::::Valor ____________________ 1::::token:::Value:::" + tokenOTP.getId()
					+ "Seteado CORRECTAMENTE::::");
			
			log.info("::::[delete]:::SQL generado:::" + stmt.toString() + "::::");

			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[delete]::::Datos guardado correctamente::::");
			log.info("::::[delete]::::Cantidad de datos eliminados::::value::::" + rs.getFetchSize() + "::::");
			resDao.setCodigo(Constantes.SUCCES);
			resDao.setMensaje(Constantes.OK);
			log.info("::::[delete]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[delete]::::Error generico en insertar nuevo token::::");
			log.info("::::[ERROR]::::[delete]::::Mensaje::::" + e.getMessage() + "::::");
			e.printStackTrace();
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}

		log.info("::::[FIN]::::[insertToken]::::Fin de DAO TokenOTP::::");
		return resDao;
	}

}
