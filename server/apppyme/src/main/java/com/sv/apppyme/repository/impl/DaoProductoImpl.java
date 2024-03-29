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
import com.sv.apppyme.entities.Categoria;
import com.sv.apppyme.entities.Desarrolladora;
import com.sv.apppyme.entities.Img;
import com.sv.apppyme.entities.Plataforma;
import com.sv.apppyme.entities.Producto;
import com.sv.apppyme.repository.IRepoProducto;
import com.sv.apppyme.utils.Constantes;
import com.sv.apppyme.utils.DateUtils;
import com.sv.apppyme.utils.Log4jUtils;

@Repository
public class DaoProductoImpl implements IRepoProducto {

	Logger log = Logger.getLogger(getClass());

	// Nombre de la tabla
	public static final String DB_TABLA_PRODUCTO = "producto";

	// Columnas de la tabla
	public static final String COL_ID = "id";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_CATEGORIA = "categoria_id";
	public static final String COL_PRECIO = "precio";
	public static final String COL_FECHA_LANZAMIENTO = "fechalanzamiento";
	public static final String COL_DESARROLLADORA = "desarrolladora_id";
	public static final String COL_IMG = "img_id";
	public static final String COL_CANTIDAD_DISPONIBLE = "cantidaddisponible";
	public static final String COL_PLATAFORMA = "plataforma_id";
	public static final String COL_DESCRIPCION = "descripcion";
	public static final String COL_FECHA_PUBLICACION = "fechapublicacion";

	// Consultas de la tabla
	public static final String SQL_INSERT = "INSERT INTO " + DB_TABLA_PRODUCTO
			+ "(" 
				+ COL_NOMBRE + ", " 
				+ COL_CATEGORIA + ", " 
				+ COL_PRECIO + ", " 
				+ COL_FECHA_LANZAMIENTO + ", " 
				+ COL_DESARROLLADORA + ", " 
				+ COL_IMG + ", " 
				+ COL_CANTIDAD_DISPONIBLE + ", "
				+ COL_PLATAFORMA + ", " 
				+ COL_DESCRIPCION + ", " 
				+ COL_FECHA_PUBLICACION
			+ ")"
		+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT = "SELECT * FROM " + DB_TABLA_PRODUCTO;
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM " + DB_TABLA_PRODUCTO + " WHERE " + COL_ID + " = ?";
	public static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM " + DB_TABLA_PRODUCTO + " WHERE " + COL_NOMBRE + " = ?";
	public static final String SQL_UPDATE = "UPDATE " + DB_TABLA_PRODUCTO 
			+ " SET " 
				+ COL_NOMBRE + " = ?, "
				+ COL_CATEGORIA + " = ?, "
				+ COL_PRECIO + " = ?, "
				+ COL_FECHA_LANZAMIENTO + " = ?, "
				+ COL_DESARROLLADORA + " = ?, "
				+ COL_IMG + " = ?, "
				+ COL_CANTIDAD_DISPONIBLE + " = ?, "
				+ COL_PLATAFORMA + " = ?, "
				+ COL_DESCRIPCION + " = ?, "
				+ COL_FECHA_PUBLICACION + " = ? "
			+ "WHERE " 
				+ COL_ID + " = ?";
	public static final String SQL_DELETE = "DELETE FROM " + DB_TABLA_PRODUCTO + " WHERE " + COL_ID + " = ?";

	@Override
	public SuperGenericResponse insert(Producto producto) {
		log.info("::::[Incio]::::[insert]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[insert]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_INSERT);
			log.info("::::[insert]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[insert]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, producto.getNombre());
			log.info("::::[insert]::::Valor ____________________ 1::::Nombre:::Value:::" + producto.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(2, producto.getCategoria().getId());
			log.info("::::[insert]::::Valor ____________________ 2::::Categoria_id:::Value:::" + producto.getCategoria().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setFloat(3, (float) producto.getPrecio());
			log.info("::::[insert]::::Valor ____________________ 3::::Precio:::Value:::" + producto.getPrecio() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDate(4, DateUtils.convertirDateJavaToDateSQL(producto.getFechaDeLanzamiento()));
			log.info("::::[insert]::::Valor ____________________ 4::::Fecha de lanzamiento:::Value:::" + DateUtils.convertirDateJavaToDateSQL(producto.getFechaDeLanzamiento()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(5, producto.getDesarrolladora().getId());
			log.info("::::[insert]::::Valor ____________________ 5::::Desarrolladora_id:::Value:::" + producto.getDesarrolladora().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(6, producto.getImg().getId());
			log.info("::::[insert]::::Valor ____________________ 6::::Img_id:::Value:::" + producto.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(7, producto.getCantidadDisponible());
			log.info("::::[insert]::::Valor ____________________ 7::::Cantidad disponible:::Value:::" + producto.getCantidadDisponible() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(8, producto.getPlataforma().getId());
			log.info("::::[insert]::::Valor ____________________ 8::::Plataforma_id:::Value:::" + producto.getPlataforma().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(9, producto.getDescripcion());
			log.info("::::[insert]::::Valor ____________________ 9::::Descripcion:::Value:::" + producto.getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(10, DateUtils.convertDateToTimeStamp(producto.getFechaPublicacion()));
			log.info("::::[insert]::::Valor ____________________ 10::::fecha_publicacion:::Value:::" + DateUtils.convertDateToTimeStamp(producto.getFechaPublicacion()) + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse update(Producto producto) {
		log.info("::::[Incio]::::[update]::::Iniciando implementacion del DAO::::");
		SuperGenericResponse res = new SuperGenericResponse();
		try {
			Connection con = ConexionPostgres.getConnecion();
			log.info("::::[update]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, SQL_UPDATE);
			log.info("::::[update]::::PreparedStatmente CREADO correctamente::::");
			log.info("::::[update]:::: Seteando valores al PreparedStatment... ::::");
			stmt.setString(1, producto.getNombre());
			log.info("::::[update]::::Valor ____________________ 1::::Nombre:::Value:::" + producto.getNombre() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(2, producto.getCategoria().getId());
			log.info("::::[update]::::Valor ____________________ 2::::Categoria_id:::Value:::" + producto.getCategoria().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDouble(3, producto.getPrecio());
			log.info("::::[update]::::Valor ____________________ 3::::Precio:::Value:::" + producto.getPrecio() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setDate(4, DateUtils.convertirDateJavaToDateSQL(producto.getFechaDeLanzamiento()));
			log.info("::::[update]::::Valor ____________________ 4::::Fecha de lanzamiento:::Value:::" + DateUtils.convertirDateJavaToDateSQL(producto.getFechaDeLanzamiento()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(5, producto.getDesarrolladora().getId());
			log.info("::::[update]::::Valor ____________________ 5::::Desarrolladora_id:::Value:::" + producto.getDesarrolladora().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(6, producto.getImg().getId());
			log.info("::::[update]::::Valor ____________________ 6::::Img_id:::Value:::" + producto.getImg().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(7, producto.getCantidadDisponible());
			log.info("::::[update]::::Valor ____________________ 7::::Cantidad disponible:::Value:::" + producto.getCantidadDisponible() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(8, producto.getPlataforma().getId());
			log.info("::::[update]::::Valor ____________________ 8::::Plataforma_id:::Value:::" + producto.getPlataforma().getId() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setString(9, producto.getDescripcion());
			log.info("::::[update]::::Valor ____________________ 9::::Descripcion:::Value:::" + producto.getDescripcion() + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setTimestamp(10, DateUtils.convertDateToTimeStamp(producto.getFechaPublicacion()));
			log.info("::::[update]::::Valor ____________________ 10::::Descripcion:::Value:::" + DateUtils.convertDateToTimeStamp(producto.getFechaPublicacion()) + "::::" + "Seteado CORRECTAMENTE:::");
			stmt.setInt(11, producto.getId());
			log.info("::::[update]::::Valor ____________________ 11::::fecha_publicacion:::Value:::" +producto.getId() + "::::" + "Seteado CORRECTAMENTE:::");
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
	public SuperGenericResponse delete(Producto producto) {
		log.info("::::[INCIO]::::[delete]::::Incio de DAO::::");
		SuperGenericResponse res = new SuperGenericResponse(Constantes.ERROR, Constantes.FAIL);

		try {
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[delete]::::Conexcion creada correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_DELETE);
			log.info("::::[delete]::::PreparedStatment creado correctamente::::");
			stmt.setInt(1, producto.getId());
			log.info("::::[delete]::::Valor ____________________ 1::::categoria:::Value:::" + producto.getId() + "Seteado CORRECTAMENTE::::");
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
	public GenericEntityResponse<Producto> getOneById(int id) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Producto> res = new GenericEntityResponse<>();
		try {
			Producto producto = new Producto();
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
				producto.setId(rs.getInt(COL_ID));
				producto.setNombre(rs.getString(COL_NOMBRE));
				producto.setCategoria(new Categoria(rs.getInt(COL_CATEGORIA)));
				producto.setPrecio( (float) rs.getDouble(COL_PRECIO));
				producto.setFechaDeLanzamiento(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_LANZAMIENTO)));
				producto.setDesarrolladora(new Desarrolladora(rs.getInt(COL_DESARROLLADORA)));
				producto.setImg(new Img(rs.getInt(COL_IMG)));
				producto.setCantidadDisponible(rs.getInt(COL_CANTIDAD_DISPONIBLE));
				producto.setPlataforma(new Plataforma(rs.getInt(COL_PLATAFORMA)));
				producto.setDescripcion(rs.getString(COL_DESCRIPCION));
				producto.setFechaPublicacion(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA_PUBLICACION)));
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
			res.setEntity(producto);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[insert]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insert]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insert]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insert]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}

	@Override
	public GenericEntityResponse<List<Producto>> getAll() {
		log.info("::::[Incio]::::[getAll]::::Iniciando implementacion del DAO para los roles::::");
		Producto producto;
		List<Producto> ls = new ArrayList<>();
		GenericEntityResponse<List<Producto>> res = new GenericEntityResponse<>();
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
				producto = new Producto();
				producto.setId(rs.getInt(COL_ID));
				producto.setNombre(rs.getString(COL_NOMBRE));
				producto.setCategoria(new Categoria(rs.getInt(COL_CATEGORIA)));
				producto.setPrecio( (float) rs.getDouble(COL_PRECIO));
				producto.setFechaDeLanzamiento(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_LANZAMIENTO)));
				producto.setDesarrolladora(new Desarrolladora(rs.getInt(COL_DESARROLLADORA)));
				producto.setImg(new Img(rs.getInt(COL_IMG)));
				producto.setCantidadDisponible(rs.getInt(COL_CANTIDAD_DISPONIBLE));
				producto.setPlataforma(new Plataforma(rs.getInt(COL_PLATAFORMA)));
				producto.setDescripcion(rs.getString(COL_DESCRIPCION));
				producto.setFechaPublicacion(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA_PUBLICACION)));
				ls.add(producto);
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
	public GenericEntityResponse<Producto> getOneByNombre(String nombre) {
		log.info("::::[Incio]::::[getOneById]::::Iniciando implementacion del DAO::::");
		GenericEntityResponse<Producto> res = new GenericEntityResponse<>();
		try {
			Producto producto = new Producto();
			Connection conn = ConexionPostgres.getConnecion();
			log.info("::::[getOneById]::::Conexion CREADO correctamente::::");
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(conn, SQL_SELECT_BY_NOMBRE);
			log.info("::::[getOneById]::::PreparedStatment CREADO correctamente::::");
			log.info("::::[getOneById]::::Seteando datos al PreparedStatment::::");
			stmt.setString(1, nombre);
			log.info("::::[getOneById]::::Valor ____________________ 1::::Nombre:::Value:::" + nombre + "Seteado CORRECTAMENTE:::");
			log.info("::::[getOneById]:::SQL generado:::" + stmt.toString() + "::::");
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			log.info("::::[getOneById]::::ResultSet CREADO correctamente::::");
			log.info("::::[getOneById]::::Interpretando Data recibida::::");
			while(rs.next()) {
				producto.setId(rs.getInt(COL_ID));
				producto.setNombre(rs.getString(COL_NOMBRE));
				producto.setCategoria(new Categoria(rs.getInt(COL_CATEGORIA)));
				producto.setPrecio( (float) rs.getDouble(COL_PRECIO));
				producto.setFechaDeLanzamiento(DateUtils.convertirDateSQLToDateJava(rs.getDate(COL_FECHA_LANZAMIENTO)));
				producto.setDesarrolladora(new Desarrolladora(rs.getInt(COL_DESARROLLADORA)));
				producto.setImg(new Img(rs.getInt(COL_IMG)));
				producto.setCantidadDisponible(rs.getInt(COL_CANTIDAD_DISPONIBLE));
				producto.setPlataforma(new Plataforma(rs.getInt(COL_PLATAFORMA)));
				producto.setDescripcion(rs.getString(COL_DESCRIPCION));
				producto.setFechaPublicacion(DateUtils.convertTimeStampToDate(rs.getTimestamp(COL_FECHA_PUBLICACION)));
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
			res.setEntity(producto);
		} catch (SQLException e) {
			log.info("::::[ERROR]::::[insert]::::Error de SQL en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insert]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		} catch (Exception e) {
			log.info("::::[ERROR]::::[insert]::::Error Generico en la implementacion del DAO::::");
			log.info("::::[ERROR]::::[insert]::::Mensaje::::" + e.getMessage() + "::::");
			log.info("::::[ERROR]::::[insert]::::Imprimiendo stacktrace::::");
			log.info("--------------------------------------------");
			log.info("An exception occurred: " + Log4jUtils.getStackTrace(e));
			log.info("--------------------------------------------");
			res.setCodigo(Constantes.ERROR);
			res.setMensaje(e.getMessage());
		}
		return res;
	}

}
