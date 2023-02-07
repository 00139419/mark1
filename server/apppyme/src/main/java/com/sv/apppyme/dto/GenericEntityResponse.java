package com.sv.apppyme.dto;

import java.io.Serializable;
import java.util.List;

public class GenericEntityResponse<E> extends SuperGenericResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private E entity;
	private List<E> listaEntity;

	public GenericEntityResponse() {
		super();
	}

	public GenericEntityResponse(GenericEntityResponse<E> cn) {
		super();
		this.setCodigo(getCodigo());
		this.setMensaje(getMensaje());
		this.entity = cn.getEntity();
		this.listaEntity = cn.listaEntity;
	}

	public GenericEntityResponse(int codigo, String mensaje) {
		super();
		this.setCodigo(codigo);
		this.setMensaje(mensaje);
	}
	
	
	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public List<E> getListaEntity() {
		return listaEntity;
	}

	@SuppressWarnings("unchecked")
	public void setListaEntity(Object listaEntity) {
		this.listaEntity = (List<E>) listaEntity;
	}

}
