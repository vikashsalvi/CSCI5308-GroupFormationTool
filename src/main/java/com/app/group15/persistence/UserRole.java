package com.app.group15.persistence;

public enum UserRole {
	STUDENT("STUDENT"),
	INSTRUCTOR("INSTRUCTOR"),
	TA("TA"),
	GUEST("GUEST"),
	ADMIN("ADMIN");

    UserRole(String role) {
		this.role=role;
	}

	public String getRole() {
		return role;
	}

	private final String role;

}
