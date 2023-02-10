package com.sv.apppyme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dao.IUsuarioDao;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Usuario;
import com.sv.apppyme.utils.Constantes;

@Service
public class UsuarioDaoImpl implements IUsuarioDao {
	
	Logger log = Logger.getLogger(UsuarioDaoImpl.class);
	
	public static final String SQL_SELECT = "SELECT * FROM usuario";
	public static final String SQL_INSERT = "INSERT INTO usuario (username, password, rol_id) VALUES (?,?,?)";

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
	
	

}
