package com.project.api.ventarecargas.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.project.api.ventarecargas.security.dto.LoginDTO;
import com.project.api.ventarecargas.security.jwt.jwtProvider;

public interface AuthServicesImpl {
	
	public String login(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, jwtProvider jwtProvider, LoginDTO loginDTO);
	
	public String registro();

}
