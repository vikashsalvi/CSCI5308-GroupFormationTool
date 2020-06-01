package com.app.group15.persistence;

public enum Environments {
	DEV("DEVINT"), TEST("TEST"), PROD("PRODUCTION");

	Environments(String environment) {
		this.environment = environment;
	}

	public String getEnvironment() {
		return environment;
	}

	private final String environment;

}
