package com.sv.apppyme.repository;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.dto.SuperGenericResponse;
import com.sv.apppyme.entities.Img;

public interface IRepoImg {
	SuperGenericResponse insert(Img img);
	SuperGenericResponse update(Img img);
	SuperGenericResponse delete(Img img);
	GenericEntityResponse<Img> getOneById(int id);
	GenericEntityResponse<List<Img>> getAll();
}