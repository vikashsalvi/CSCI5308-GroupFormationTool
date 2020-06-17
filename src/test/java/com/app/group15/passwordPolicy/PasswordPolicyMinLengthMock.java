package com.app.group15.passwordPolicy;

import com.app.group15.PasswordPolicyManagement.IPasswordPolicyValidator;

public class PasswordPolicyMinLengthMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int minimumLengthAllowed = 8;

		if (password.length() >= minimumLengthAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
