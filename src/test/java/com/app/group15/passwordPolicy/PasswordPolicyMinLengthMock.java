package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyValidator;
import com.app.group15.passwordPolicyManagement.PasswordPolicy;
import com.app.group15.passwordPolicyManagement.PasswordPolicyAbstractDao;

import java.util.List;

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
