package org.swss.security.model;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class ForgorPasswordModel {
	
	@NotEmpty(message="Invalid forgot password process.")
	private String changeToken;

	private String email;
	
	@NotEmpty(message="Username cannot be empty.")
	private String username;
	
	@NotEmpty(message="New password cannot be empty.")
	@Length(min=3,max=30,message="New password does not meet length requirement.")
	private String newPassword;
	
	public ForgorPasswordModel() { super(); }

	public String getChangeToken() {
		return changeToken;
	}

	public void setChangeToken(String changeToken) {
		this.changeToken = changeToken;
	}

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
