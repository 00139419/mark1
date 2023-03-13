package com.sv.apppyme.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.date.DateUtilities;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Videojuego;
import com.sv.apppyme.repository.IRepoVideojuego;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.Log4jUtils;

public class VideojuegoDao implements IRepoVideojuego {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_VIDEOJUEGO = "videojuego";

	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_CATEGORIA = "categoria_id";
	public static final String COL_PRECIO = "precio";
	public static final String COL_FECHA_LANZAMIENTO = "fechalanzamiento";
	public static final String COL_DESARROLLADORA = "desarrolladora_id";
	public static final String COL_IMG = "img_id";
	public static final String COL_CANTIDAD_DISPONIBLE = "cantidaddisponible";
	public static final String COL_PLATAFORMA = "plataforma";
	public static final String COL_DESCRIPCION = "descripcion";

	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_VIDEOJUEGO
			+ "(" 
				+ COL_NOMBRE + ", " 
				+ COL_CATEGORIA + ", " 
				+ COL_PRECIO + ", " 
				+ COL_FECHA_LANZAMIENTO + ", " 
				+ COL_DESARROLLADORA + ", " 
				+ COL_IMG + ", " 
				+ COL_CANTIDAD_DISPONIBLE + ", "
				+ COL_PLATAFORMA + ", " 
				+ COL_DESCRIPCION 
			+ ")"
		+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_VIDEOJUEGO;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_VIDEOJUEGO + " WHERE " + COL_ID + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_VIDEOJUEGO 
			+ " SET " 
				+ COL_ID + " = ?, " 
				+ COL_NOMBRE + " = ? "
			+ "WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_VIDEOJUEGO + " WHERE " + COL_ID + " = ?";

	@Override
	public SuperGenericResponse insert(Videojuego videojuego) {
		log.info("::::[Incio]::::[insert]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insert]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insert]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, videojuego.getNombre());
			log.info("::::[insert]::::Valor ____________________ 1::::Nombre:::Value:::" + videojuego.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(2, videojuego.getCategoria().getId());
			log.info("::::[insert]::::Valor ____________________ 2::::Categoria_id:::Value:::" + videojuego.getCategoria().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(3, videojuego.getPrecio());
			log.info("::::[insert]::::Valor ____________________ 3::::Precio:::Value:::" + videojuego.getPrecio() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDate(4, DateUtils.convertirDateJavaToDateSQL(videojuego.getFechaDeLanzamiento()));
			log.info("::::[insert]::::Valor ____________________ 4::::Fecha de lanzamiento:::Value:::" + DateUtils.convertirDateJavaToDateSQL(videojuego.getFechaDeLanzamiento()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(5, videojuego.getDesarrolladora().getId());
			log.info("::::[insert]::::Valor ____________________ 5::::Desarrolladora_id:::Value:::" + videojuego.getDesarrolladora().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(6, videojuego.getImg().getId());
			log.info("::::[insert]::::Valor ____________________ 6::::Img_id:::Value:::" + videojuego.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(7, videojuego.getCantidadDisponible());
			log.info("::::[insert]::::Valor ____________________ 7::::Cantidad disponible:::Value:::" + videojuego.getCantidadDisponible() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(8, videojuego.getPlataforma().getId());
			log.info("::::[insert]::::Valor ____________________ 8::::Plataforma_id:::Value:::" + videojuego.getPlataforma().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(9, videojuego.getDescripcion());
			log.info("::::[insert]::::Valor ____________________ 9::::Descripcion:::Value:::" + videojuego.getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			log.info("::::[insert]:::SQL generado:::" + stmt.toString() + "::::");
			int resultado = ConexionPostgres.updateQuery(stmt);
			log.info("::::[insert]::::stmt ejecutado correctamente::::");
			log.info("::::[insert]::::Interpretando Data recibida::::");
			if(resultado < 0) {
				res.setCodigo(Constantes.SUCCES);
				res.setMensaje(Constantes.OK);
				log.info("::::[insert]::::Se crearon " + resultado + " nuevos registros\"::::");
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
	public SuperGenericResponse update(Videojuego videojuego) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperGenericResponse delete(Videojuego videojuego) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<Videojuego> getOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntityResponse<List<Videojuego>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
