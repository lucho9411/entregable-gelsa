package com.project.api.ventarecargas.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.project.api.ventarecargas.security.covers.MessageCover;
import com.project.api.ventarecargas.security.dto.LoginDTO;
import com.project.api.ventarecargas.security.dto.UsersDTO;
import com.project.api.ventarecargas.security.jwt.jwtProvider;
import com.project.api.ventarecargas.security.repositories.UsuariosRepository;

@Service
public class AuthServicesJPA implements AuthServicesImpl {
	
	@Autowired
	private UsuariosRepository usuariosRepo;
	
	@Override
	public String login(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, jwtProvider jwtProvider, LoginDTO loginDTO) {
		String json1 = null, json2 = null;
		MessageCover messageCover = null;
		UsersDTO userDTO = new UsersDTO();
		try {
			 authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPassword()));
	        var user = usuariosRepo.findByNombre(loginDTO.getUser())
	                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
	        var jwt = jwtProvider.generateToken(user);
	        userDTO.setName(loginDTO.getUser());
	        userDTO.setToken(jwt);
	        messageCover = new MessageCover(200, null);
			json1 = new Gson().toJson(messageCover);
			json2 = new Gson().toJson(userDTO);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(404, null);
			json1 = new Gson().toJson(messageCover);
			json2 = new Gson().toJson(null);
		}
		return "[" + json1 + "," + json2 + "]";
	}

	@Override
	public String registro() {
		// TODO Auto-generated method stub
		return null;
	}

}
