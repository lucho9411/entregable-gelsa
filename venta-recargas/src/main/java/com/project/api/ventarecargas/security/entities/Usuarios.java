package com.project.api.ventarecargas.security.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Usuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre;
	@Column(name = "clave", nullable = false)
	private String clave;
	@Column(name = "estado", nullable = false)
	private int estado;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario"), inverseJoinColumns = @JoinColumn(name = "rol"))
	private Set<Roles> roles = new HashSet<>();
	

}
