package com.project.api.ventarecargas.security.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LoginDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(required = true)
	private String User;
	@JsonProperty(required = true)
	private String Password;

}
