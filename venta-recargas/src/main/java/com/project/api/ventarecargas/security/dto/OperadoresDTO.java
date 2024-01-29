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
public class OperadoresDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(required = false)
	private long IdOperador;
	@JsonProperty(required = true)
	private String NombreOperador;
	@JsonProperty(required = true)
	private String ImagenOperador;

}
