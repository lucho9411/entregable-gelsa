package com.project.api.ventarecargas.security.services;

import java.util.Optional;
import com.project.api.ventarecargas.security.dto.UsersDTO;
import com.project.api.ventarecargas.security.entities.Usuarios;

public interface UsuariosServicesImpl {
	
	public Optional<Usuarios> getByNombre(String nombre);
	
	public UsersDTO consultarUsuario(long id);
	public Usuarios consultarUsuarioEntity(long id);

}
