package com.project.api.ventarecargas.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="usuarios_roles")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class UsuariosRoles {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	@ManyToOne
	@JoinColumn(referencedColumnName= "id", name= "usuario")
	private Usuarios user;
	@ManyToOne
	@JoinColumn(referencedColumnName= "id", name= "rol")
	private Roles role;
}
