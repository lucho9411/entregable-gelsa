package com.project.api.ventarecargas.security.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "operadores")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Operadores implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
	@Column(name = "imagen", nullable = true)
    private String imagen;

}
