package com.sv.apppyme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sv.apppyme.conexciones.ConexionPostgres;
import com.sv.apppyme.utils.Constantes;

@SpringBootApplication
public class ApppymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApppymeApplication.class, args);
		
		try {
			
			String query = "SELECT * FROM " + Constantes.DB_TABLA_ROL;
			
			Connection con = ConexionPostgres.getConnecion();
			PreparedStatement stmt = ConexionPostgres.getPreparedStatement(con, query);
			ResultSet rs = ConexionPostgres.executeQuery(stmt);
			
			while(rs.next()) {
			System.out.println("Rol id" + rs.getInt(Constantes.DB_TABLA_ROL_COL_ID));
			System.out.println("Rol descripcion" + rs.getString(Constantes.DB_TABLA_ROL_COL_DESCRIPCION));
			System.out.println("");
			}
			
			
			System.out.println("FInal");
			
		} catch (Exception e) {
			System.out.println("ERROR!");
			e.printStackTrace();
		}
		
	}

}
