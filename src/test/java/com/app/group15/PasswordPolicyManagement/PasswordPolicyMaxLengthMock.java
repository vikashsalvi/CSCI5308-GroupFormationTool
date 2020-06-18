package com.app.group15.PasswordPolicyManagement;

public class PasswordPolicyMaxLengthMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int maximumLengthAllowed = 16;

		if (password.length() <= maximumLengthAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
