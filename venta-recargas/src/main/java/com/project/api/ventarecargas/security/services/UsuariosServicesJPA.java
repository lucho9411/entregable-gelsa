package com.project.api.ventarecargas.security.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.ventarecargas.security.dto.UsersDTO;
import com.project.api.ventarecargas.security.entities.Usuarios;
import com.project.api.ventarecargas.security.repositories.UsuariosRepository;

@Service
public class UsuariosServicesJPA implements UsuariosServicesImpl {
	
	@Autowired
	private UsuariosRepository usuariosRepo;
	
	public Optional<Usuarios> getByNombre(String nombre){
		return usuariosRepo.findByNombre(nombre);
	}

	@SuppressWarnings("deprecation")
	@Override
	public UsersDTO consultarUsuario(long id) {
		UsersDTO usuarioDTO = new UsersDTO();
		Usuarios usuario = usuariosRepo.getById(id);
		try {
			usuarioDTO.setIdUser(usuario.getId());
			usuarioDTO.setName(usuario.getNombre());
			usuarioDTO.setStatus(usuario.getEstado());
			return usuarioDTO;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuarios consultarUsuarioEntity(long id) {
		try {
			if (usuariosRepo.findById(id).isPresent()) {
				return usuariosRepo.findById(id).get();
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
