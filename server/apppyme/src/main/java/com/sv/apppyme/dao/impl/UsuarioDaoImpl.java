package com.sv.apppyme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dao.IUsuarioDao;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.utils.Constantes;

@Service
public class UsuarioDaoImpl implements IUsuarioDao {
	
	//nombre de la tabla
	public static final String DB_TABLA_USUARIO = "usuario";
	
	//columnas de la tabla usuario
	public static final String COL_ID = "id";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	public static final String COL_ROL_ID = "rol_id";
	
	Logger log = Logger.getLogger(UsuarioDaoImpl.class);
	
	//querys de la tabla usuario
	public static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM " + DB_TABLA_USUARIO + " WHERE username = ?";
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_USUARIO + " (username, password, rol_id) VALUES (?,?,?)";
	
	@Override
	public SuperGenericResponse insertar(Usuario usuario) {
		log.info("::::[Incio]::::[insertar]::::Iniciando implementacion del DAO para los usuarios::::");
		SuperGenericResponse resDao = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insertar]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insertar]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insertar]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, usuario.getUsername());
			log.info("::::[insertar]::::Valor -> 1::::Nombre:::Value:::" + usuario.getUsername() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(2, usuario.getPassword());
			log.info("::::[insertar]::::Valor -> 2::::Password:::Value:::" + usuario.getPassword() + ":::"  +  "Seteado CORRECTAMENTE:::");
			stmt.setInt(3, usuario.getRol().getId());
			log.info("::::[insertar]::::Valor -> 3::::Rol:::Value:::" + usuario.getRol().getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[insertar]::::stmt ejecutado correctamente::::");
			log.info("::::[insertar]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				resDao.setCodigo(Constantes.SUCCES);
				resDao.setMensaje(Constantes.OK);
				log.info("::::[insertar]::::Se crearon " + resultado + " nuevos usuarios\"::::");
				log.info("::::[insertar]::::Enviando repsuesta del implementacion del DAO::::");
			}
			log.info("::::[getAll]::::Fin interpretando Data recibida::::");
			stmt.close();
			log.info("::::[getAll]::::PreparedStatement CERRADO correctamente::::");
			con.close();
			log.info("::::[getAll]::::Conexion CERRADO correctamente::::");
			log.info("::::[getAll]::::Enviando repsuesta del implementacion del DAO::::");
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[insertar]::::Error de SQL en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[insertar]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insertar]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insertar]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[insertar]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insertar]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resDao.setCodigo(Constantes.ERROR);
			resDao.setMensaje(Constantes.FAIL);
		}
		log.info("::::[FIN]::::[getAll]::::Fin implementacion del DAO para los roles::::");
		return resDao;
	}

	@Override
	public GenericEntityResponse<Usuario> selectByUsername(String username) {
		GenericEntityResponse<Usuario> resEntity = new GenericEntityResponse<>();
		try {
			Usuario usuario = null;
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[insertar]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_USERNAME);
			log.info("::::[insertar]::::PreparedStatment CREADO correctamente::::");
			stmt.setString(1, username);
			log.info("::::[insertar]::::Valor -> 1::::Username:::Value:::" + username + "::::" + "Seteado CORRECTAMENTE:::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[insertar]::::ResultSet CREADO correctamente::::");
			log.info("::::[insertar]::::Interpretando Data recibida::::");
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt(COL_ID));
				usuario.setUsername(rs.getString(COL_USERNAME));
				usuario.setRol(new Rol(rs.getInt(COL_ROL_ID)));
				usuario.setPassword(rs.getString(COL_PASSWORD));
			}
			log.info("::::[selectByUsername]::::Fin interpretando Data recibida::::");
			rs.close();
			log.info("::::[selectByUsername]::::ResultSet CERRADO correctamente::::");
			stmt.close();
			log.info("::::[selectByUsername]::::PreparedStatement CERRADO correctamente::::");
			conn.close();
			log.info("::::[selectByUsername]::::Conexion CERRADO correctamente::::");
			log.info("::::[selectByUsername]::::Enviando repsuesta del implementacion del DAO::::");
			resEntity.setCodigo(Constantes.SUCCES);
			resEntity.setMensaje(Constantes.OK);
			resEntity.setEntity(usuario);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[selectByUsername]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[selectByUsername]::::Error Generico en la implementacion del DAO Rol::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[selectByUsername]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			e.printStackTrace();
			log.info("--------------------------------------------");
			resEntity.setCodigo(Constantes.ERROR);
			resEntity.setMensaje(e.getMessage());
		}
		return resEntity;
	}
	
	

}
