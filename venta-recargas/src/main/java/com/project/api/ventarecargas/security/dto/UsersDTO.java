package com.project.api.ventarecargas.security.dto;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UsersDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(required = false)
	private long IdUser;
	@JsonProperty(required = true)
	private String Name;
	@JsonProperty(required = false)
	private String Password;
	@JsonProperty(required = true)
	private int Status;
	@JsonProperty(required = false)
	private List<RolesDTO> RolesInfo;
	@JsonProperty(required = false)
	private Object token;
}
