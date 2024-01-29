package com.project.api.ventarecargas.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.api.ventarecargas.security.jwt.jwtProvider;
import com.project.api.ventarecargas.security.inventory.EndPointsSeguridad;
import com.project.api.ventarecargas.security.dto.LoginDTO;
import com.project.api.ventarecargas.security.services.AuthServicesImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = EndPointsSeguridad.RUTA_PRINCIPAL)
public class SeguridadController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	jwtProvider jwtProvider;
	@Autowired
	private AuthServicesImpl authImpl; 
	
	@GetMapping(value = EndPointsSeguridad.GENERAR_PASSWORD)
	public String generatePw(@RequestParam("Clave") String Clave) {
		return passwordEncoder.encode(Clave);
	}
	
	@PostMapping(value = EndPointsSeguridad.LOGIN)
	public String login(@RequestBody() LoginDTO loginDTO) {
        return authImpl.login(passwordEncoder, authenticationManager, jwtProvider, loginDTO);
	}

}
