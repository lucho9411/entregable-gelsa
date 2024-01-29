package com.project.api.ventarecargas.security.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecargasDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(required = false)
	private long IdRecarga;
	@JsonProperty(required = false)
	private String FechaRecarga;
	@JsonProperty(required = true)
	private String NumeroTelefono;
	@JsonProperty(required = true)
	private double Precio;
	@JsonProperty(required = false)
	private UsersDTO Vendedor;
	@JsonProperty(required = true)
	private OperadoresDTO Operador;

}
