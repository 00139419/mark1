package com.sv.apppyme.controllers.dto;

import lombok.Data;

@Data
public class VideoJuegoDto {
	private String nombre;
	private int categoria;
	private float precio;
	private String fechaDeLanzamiento;
	private int desarrolladora;
	private int cantidadDisponible;
	private int plataforma;
	private String descripcion;
	private String imgbase64;
}
