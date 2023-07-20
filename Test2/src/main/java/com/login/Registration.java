package com.login;

public class Registration {
	private String username;
	private String password;
	private String fullName;
	private String email;
	private String type;

	// Default constructor
	public Registration() {
	}

	// Parameterized constructor
	public Registration(String username, String password, String fullName, String email, String type) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.type = type;
	}

	// Getters and setters
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
