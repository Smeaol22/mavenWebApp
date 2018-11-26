package com.apirest.faq.dao;

public class EncryptedPassword {

	private String password;

	public EncryptedPassword() {
	}

	public EncryptedPassword(String login, String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
