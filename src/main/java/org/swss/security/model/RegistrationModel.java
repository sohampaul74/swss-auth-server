package org.swss.security.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class RegistrationModel {
	private String email;
	
	@NotEmpty(message="Username cannot be empty.")
	private String username;
	
	@NotEmpty(message="Password cannot be empty.")
	@Length(min=3,max = 30, message = "Password does not meet length requirements.")
	private String password;
	
	public RegistrationModel() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
