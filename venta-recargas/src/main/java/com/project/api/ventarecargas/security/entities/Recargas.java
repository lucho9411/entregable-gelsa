package com.project.api.ventarecargas.security.entities;

import jakarta.persistence.Column;
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
@Table(name="recargas")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Recargas {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	@Column(name = "fecha_hora", nullable = true)
	private java.sql.Timestamp fechaHora;
	@Column(name = "numero")
	private String numero;
	@Column(name = "valor")
	private double valor;
	@ManyToOne
	@JoinColumn(referencedColumnName= "id", name= "fk_vendedor")
	private Usuarios user;
	@ManyToOne
	@JoinColumn(referencedColumnName= "id", name= "fk_operador")
	private Operadores operador;

}
