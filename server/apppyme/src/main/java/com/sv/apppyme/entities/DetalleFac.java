package com.sv.apppyme.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetalleFac {

	private int id;
	private CabeceraFac cabeceraFac;
	private Producto producto;
	private int cantidad;
	private float precioUnitario;
	private float subTotal;

	public DetalleFac() {
		super();
	}

}
