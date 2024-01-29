package com.project.api.ventarecargas.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.api.ventarecargas.security.entities.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	public boolean existsByNombre(String nombre);

}
