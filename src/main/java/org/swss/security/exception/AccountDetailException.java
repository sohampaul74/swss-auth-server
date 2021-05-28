package org.swss.security.exception;

public class AccountDetailException extends RuntimeException {
	private String identifier;
	public AccountDetailException(String message, String identifier) {
		super(message);
		this.identifier = identifier;
	}
	public String getIdentifier() {
		return this.identifier;
	}
}
