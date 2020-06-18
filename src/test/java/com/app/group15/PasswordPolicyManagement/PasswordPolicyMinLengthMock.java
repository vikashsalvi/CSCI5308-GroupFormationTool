package com.app.group15.PasswordPolicyManagement;

public class PasswordPolicyMinLengthMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int minimumLengthAllowed = 8;

		return password.length() >= minimumLengthAllowed;
	}

}
