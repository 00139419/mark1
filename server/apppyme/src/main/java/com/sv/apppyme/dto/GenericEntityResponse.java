package com.sv.apppyme.dto;

import java.util.List;

public class GenericEntityResponse<E> extends SuperGenericResponse {

	private E entity;
	private List<E> listaEntity;

	public GenericEntityResponse() {
		super();
	}

	public GenericEntityResponse(GenericEntityResponse<E> cn) {
		super();
		this.entity = cn.getEntity();
		this.listaEntity = cn.listaEntity;
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
