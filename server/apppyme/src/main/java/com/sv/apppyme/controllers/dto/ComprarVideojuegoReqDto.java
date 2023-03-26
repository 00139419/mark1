package com.sv.apppyme.controllers.dto;

import java.util.List;

import com.sv.apppyme.entities.Videojuego;

import lombok.Data;

@Data
public class ComprarVideojuegoReqDto {
	
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private List<Videojuego> lv;
	private double total;
	private double totalDineroFisicoRecibido;
	private double totalDineroFisicoCambio;
	private String metodoPago;
	
}
