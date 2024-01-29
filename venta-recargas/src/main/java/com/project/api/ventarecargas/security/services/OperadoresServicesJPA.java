package com.project.api.ventarecargas.security.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.project.api.ventarecargas.security.covers.MessageCover;
import com.project.api.ventarecargas.security.dto.OperadoresDTO;
import com.project.api.ventarecargas.security.entities.Operadores;
import com.project.api.ventarecargas.security.repositories.OperadoresRepository;

@Service
public class OperadoresServicesJPA implements OperadoresServicesImpl {
	
	@Autowired
	private OperadoresRepository operadoresRepo;
	
	private String json1 = null, json2 = null;
	private MessageCover messageCover = null;

	@Override
	public List<OperadoresDTO> listarOperadores() {
		List<OperadoresDTO> lista = new ArrayList<OperadoresDTO>();
		List<Operadores> operadores = operadoresRepo.findAll();
		try {
			for(Operadores operador: operadores) {
				OperadoresDTO operadorDTO = new OperadoresDTO();
				operadorDTO.setIdOperador(operador.getId());
				operadorDTO.setNombreOperador(operador.getNombre());
				operadorDTO.setImagenOperador(operador.getImagen());
				lista.add(operadorDTO);
			}
			return lista;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String listarOperadoresGson() {
		List<OperadoresDTO> lista = new ArrayList<OperadoresDTO>();
		List<Operadores> operadores = operadoresRepo.findAll();
		try {
			if (operadores.isEmpty()) {
				messageCover = new MessageCover(404, "Lista vacía");
				json2 = new Gson().toJson(operadores);
			}
			else {
				for(Operadores operador: operadores) {
					OperadoresDTO operadorDTO = new OperadoresDTO();
					operadorDTO.setIdOperador(operador.getId());
					operadorDTO.setNombreOperador(operador.getNombre());
					operadorDTO.setImagenOperador(operador.getImagen());
					lista.add(operadorDTO);
				}
				messageCover = new MessageCover(200, null);
				json2 = new Gson().toJson(lista);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
			json2 = new Gson().toJson(null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "," + json2 + "]";
	}

	@SuppressWarnings("deprecation")
	@Override
	public OperadoresDTO consultarOperador(long idOperador) {
		OperadoresDTO operadorDTO = new OperadoresDTO();
		Operadores operador = operadoresRepo.getById(idOperador);
		try {
			if (operador != null && operador.getId() != 0) {
				operadorDTO.setIdOperador(operador.getId());
				operadorDTO.setNombreOperador(operador.getNombre());
				operadorDTO.setImagenOperador(operador.getImagen());
				return operadorDTO;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String consultarOperadorGson(long idOperador) {
		OperadoresDTO operadorDTO = new OperadoresDTO();
		Operadores operador = operadoresRepo.getById(idOperador);
		try {
			if (operador != null && operador.getId() != 0) {
				operadorDTO.setIdOperador(operador.getId());
				operadorDTO.setNombreOperador(operador.getNombre());
				operadorDTO.setImagenOperador(operador.getImagen());
				messageCover = new MessageCover(200, null);
				json2 = new Gson().toJson(operadorDTO);
			}
			else {
				messageCover = new MessageCover(404, "No existe");
				json2 = new Gson().toJson(null);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
			json2 = new Gson().toJson(null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "," + json2 + "]";
	}

	@Override
	public Operadores consultarOperadorEntity(long id) {
		try {
			if (operadoresRepo.findById(id).isPresent()) {
				return operadoresRepo.findById(id).get();
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
