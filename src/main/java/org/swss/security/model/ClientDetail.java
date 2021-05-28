package org.swss.security.model;

public class ClientDetail {

	private final String username;
	private final String password;
	public ClientDetail(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
