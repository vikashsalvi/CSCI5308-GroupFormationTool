package com.app.group15.PasswordPolicyManagement;

public class PasswordPolicyMaxLengthMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int maximumLengthAllowed = 16;

		return password.length() <= maximumLengthAllowed;
	}

}
