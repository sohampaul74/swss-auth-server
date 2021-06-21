package org.swss.security.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ChangePasswordModel {
	
	@NotEmpty(message="Username cannot be empty.")
	private String username;
	
	@NotEmpty(message="Old password cannot be empty.")
	@Length(min=3,max=30,message="Old password does not meet length requirement.")
	private String oldPassword;
	
	@NotEmpty(message="Old password cannot be empty.")
	@Length(min=3,max=30,message="Old password does not meet length requirement.")
	private String newPassword;

	public ChangePasswordModel() { super(); }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
