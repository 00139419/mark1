package com.sv.apppyme.services;

import java.util.List;

import com.sv.apppyme.dto.GenericEntityResponse;
import com.sv.apppyme.entities.Rol;
import com.sv.apppyme.exception.SrvValidacionException;

public interface IData {
	GenericEntityResponse<List<Rol>> getAllRoles() throws SrvValidacionException;
}
