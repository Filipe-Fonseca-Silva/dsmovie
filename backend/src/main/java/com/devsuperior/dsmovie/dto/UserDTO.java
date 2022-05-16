package com.devsuperior.dsmovie.dto;

import com.devsuperior.dsmovie.entities.User;

public class UserDTO {

	private Long id;
	private String email;
	private String name;
	private String cpf;
	
	public UserDTO() {
	}

	public UserDTO(Long id, String email, String name, String cpf) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.cpf = cpf;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		email = entity.getEmail();
		name = entity.getName();
		cpf = entity.getCpf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
