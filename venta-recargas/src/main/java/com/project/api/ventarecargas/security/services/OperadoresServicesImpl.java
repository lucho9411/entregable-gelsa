package com.project.api.ventarecargas.security.services;

import java.util.List;
import com.project.api.ventarecargas.security.dto.OperadoresDTO;
import com.project.api.ventarecargas.security.entities.Operadores;

public interface OperadoresServicesImpl {
	
	public List<OperadoresDTO> listarOperadores();
	public String listarOperadoresGson();
	
	public OperadoresDTO consultarOperador(long idOperador);
	public String consultarOperadorGson(long idOperador);
	public Operadores consultarOperadorEntity(long id);

}
