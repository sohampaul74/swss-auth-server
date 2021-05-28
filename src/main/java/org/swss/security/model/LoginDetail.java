package org.swss.security.model;

public class LoginDetail {

	private final String username;
	private final String password;
	private final String grantType;
	public LoginDetail(String username, String password, String grantType) {
		super();
		this.username = username;
		this.password = password;
		this.grantType = grantType;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getGrantType() {
		return grantType;
	}
}
