package com.sv.apppyme.dto;

import java.util.List;

public class GenericEntityResponse<E> extends SuperGenericResponse {

	private E entity;
	private List<E> listaEntity;

	public GenericEntityResponse() {
		super();
	}

	public GenericEntityResponse(E entity, List<E> listaEntity) {
		super();
		this.entity = entity;
		this.listaEntity = listaEntity;
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

	public void setListaEntity(List<E> listaEntity) {
		this.listaEntity = listaEntity;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[GenericEntityResponse {entity=");
		builder.append(entity);
		builder.append(", listaEntity=");
		builder.append(listaEntity);
		builder.append("}]");
		return builder.toString();
	}

	
	
}
