package com.project.api.ventarecargas.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.api.ventarecargas.security.inventory.EndPointsGenerales;
import com.project.api.ventarecargas.security.services.OperadoresServicesImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = EndPointsGenerales.OPERADORES)
public class OperadoresController {
	
	@Autowired
	private OperadoresServicesImpl operadoresService;
	
	@GetMapping(value = EndPointsGenerales.LISTAR)
	public String listarOperadores() {
		return operadoresService.listarOperadoresGson();
	}
	
	@GetMapping(value = EndPointsGenerales.CONSULTAR)
	public String consultarOperador(@RequestParam("idOperador") long idOperador) {
		return operadoresService.consultarOperadorGson(idOperador);
	}

}
