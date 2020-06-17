package com.app.group15.Persistence;

public enum AwsSecretKey {
	DEVINT("qa-dev-secret"), TEST("qa-test-secret"), PROD("qa-prod-secret");

	AwsSecretKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	private final String key;

}
