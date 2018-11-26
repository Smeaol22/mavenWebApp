package com.apirest.faq.dao;

public class Administration {

	private String login;
	
	private String password;
	
	public Administration() {}

	public Administration(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
