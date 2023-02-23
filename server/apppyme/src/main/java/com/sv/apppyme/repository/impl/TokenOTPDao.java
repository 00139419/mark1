package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.TokenOTP;
import com.sv.apppyme.repository.ITokenOTP;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;


@Service
public class TokenOTPDao implements ITokenOTP{
	
	Logger log = Logger.getLogger(getClass());
	
	//consultas para la tabla token
	public static String SELECT_BY_TOKEN = "SELECT * FROM tokenotp WHERE token = ?";
	public static String SQL_INSERT = "INSERT INTO tokenotp (user_id, token, fechadecreacion, fechadevencimiento, esvalido, estaverificado) values (?,?,?,?,?,?)";
	
	//nombres de las columnas
	public static String COL_USER_ID = "user_id";
	public static String COL_TOKEN = "token";
	public static String COL_FECHA_CREACION = "fechadecreacion";
	public static String COL_FECHA_VENCIMIENTO = "fechadevencimiento";
	public static String COL_ES_VALIDO = "esvalido";
	public static String COL_ESTA_VERIFICADO = "estaverificado";

	@Override
	public SuperGenericResponse verificarTokenOTP(String numero) {
		log.info("::::[INCIO]::::[numeroExiste]::::Incio de DAO TokenOTP::::");
		
		SuperGenericResponse resDao = new SuperGenericResponse(-1, "Error generico");
		
		try {
			List<TokenOTP> ls = new ArrayList<>();
			TokenOTP tokenOtp = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[numeroExiste]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SELECT_BY_TOKEN);
			log.info("::::[numeroExiste]::::PreparedStatment creado correctamente::::");
			stmt.setString(1, numero);
			log.info("::::[numeroExiste]::::Valor -> 1::::token:::Value:::" + numero + "Seteado CORRECTAMENTE::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[numeroExiste]::::ResultSet creado correctamente::::");
			log.info("::::[numeroExiste]::::InterpretandoData::::");
			while(rs.next()) {
				tokenOtp = new TokenOTP();
				tokenOtp.setToken(rs.getString(COL_TOKEN));
				ls.add(tokenOtp);
			}
			log.info("::::[numeroExiste]::::Data interpretada correctamente::::");
			log.info("::::[numeroExiste]::::cantidad de campos encontrados con el mismo token::::" + ls.size() + "::::");
			if(ls.isEmpty()) {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.OK);
			}else {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.FAIL);
			}
			log.info("::::[numeroExiste]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[numeroExiste]::::Error generico en la verificacion si existe ese tokenOTP::::");
			log.info("::::[ERROR]::::[numeroExiste]::::Mensaje::::" + e.getMessage() + "::::");
			log.info(e.getStackTrace().toString());
			e.printStackTrace();
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}
		log.info("::::[numeroExiste]::::Retornando respuesta del DAO::::");
		return resDao;
	}

	@Override
	public SuperGenericResponse insertToken(TokenOTP tokenOTP) {
		log.info("::::[INCIO]::::[insertToken]::::Incio de DAO TokenOTP::::");
		SuperGenericResponse resDao = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);
		
		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[numeroExiste]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_INSERT);
			
			log.info("::::[numeroExiste]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, tokenOTP.getUsuario().getId());
			log.info("::::[numeroExiste]::::Valor -> 1::::token:::Value:::" + tokenOTP.getUsuario().getId() + "Seteado CORRECTAMENTE::::");
			stmt.setString(2, tokenOTP.getToken());
			log.info("::::[numeroExiste]::::Valor -> 2::::token:::Value:::" + tokenOTP.getToken() + "Seteado CORRECTAMENTE::::");
			stmt.setDate(3, DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeCreacion()));
			log.info("::::[numeroExiste]::::Valor -> 3::::token:::Value:::" + DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeCreacion()) + "Seteado CORRECTAMENTE::::");
			stmt.setDate(4, DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeVencimiento()));
			log.info("::::[numeroExiste]::::Valor -> 4::::token:::Value:::" + DateUtils.convertirDateJavaToDateSQL(tokenOTP.getFechaDeVencimiento()) + "Seteado CORRECTAMENTE::::");
			stmt.setBoolean(5, tokenOTP.getEsValido());
			log.info("::::[numeroExiste]::::Valor -> 5::::token:::Value:::" + tokenOTP.getEsValido() + "Seteado CORRECTAMENTE::::");
			stmt.setBoolean(6, tokenOTP.getEstaVerificado());
			log.info("::::[numeroExiste]::::Valor -> 6::::token:::Value:::" + tokenOTP.getEstaVerificado() + "Seteado CORRECTAMENTE::::");
			
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[numeroExiste]::::Datos guardado correctamente::::");
			log.info("::::[numeroExiste]::::Cantidad de datos guardados::::value::::" + resultado + "::::");
			resDao.setCodigo(Constantes.SUCCES);
			resDao.setMensaje(Constantes.OK);
			log.info("::::[numeroExiste]::::Respuesta creada correctamente::::");
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insertToken]::::Error generico en insertar nuevo token::::");
			log.info("::::[ERROR]::::[insertToken]::::Mensaje::::" + e.getMessage() + "::::");
			e.printStackTrace();
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}
		
		log.info("::::[FIN]::::[insertToken]::::Fin de DAO TokenOTP::::");
		return resDao;
	}

}
