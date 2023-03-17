package com.sv.apppyme.entities;

import java.util.Date;

public class Reporte {
	
	private int id;
	private String base64;
	private Date fecha;
	private String nombre;
	private Usuario userId;
	private String tipoDoc;
	private String numDoc;
	private float total;
	private float totalrec;
	private float totalcam;
	private String metpag;
	private String lv;
	
	public Reporte() {
		super();
	}

	public Reporte(int id, String base64, Date fecha, String nombre, Usuario userId, String tipoDoc, String numDoc,
			float total, float totalrec, float totalcam, String metpag, String lv) {
		super();
		this.id = id;
		this.base64 = base64;
		this.fecha = fecha;
		this.nombre = nombre;
		this.userId = userId;
		this.tipoDoc = tipoDoc;
		this.numDoc = numDoc;
		this.total = total;
		this.totalrec = totalrec;
		this.totalcam = totalcam;
		this.metpag = metpag;
		this.lv = lv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUserId() {
		return userId;
	}

	public void setUserId(Usuario userId) {
		this.userId = userId;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getTotalrec() {
		return totalrec;
	}

	public void setTotalrec(float totalrec) {
		this.totalrec = totalrec;
	}

	public float getTotalcam() {
		return totalcam;
	}

	public void setTotalcam(float totalcam) {
		this.totalcam = totalcam;
	}

	public String getMetpag() {
		return metpag;
	}

	public void setMetpag(String metpag) {
		this.metpag = metpag;
	}

	public String getLv() {
		return lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reporte [id=");
		builder.append(id);
		builder.append(", base64=");
		builder.append(base64);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", tipoDoc=");
		builder.append(tipoDoc);
		builder.append(", numDoc=");
		builder.append(numDoc);
		builder.append(", total=");
		builder.append(total);
		builder.append(", totalrec=");
		builder.append(totalrec);
		builder.append(", totalcam=");
		builder.append(totalcam);
		builder.append(", metpag=");
		builder.append(metpag);
		builder.append(", lv=");
		builder.append(lv);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}