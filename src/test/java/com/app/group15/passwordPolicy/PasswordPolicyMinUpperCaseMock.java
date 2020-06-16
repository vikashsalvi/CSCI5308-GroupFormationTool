package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyValidator;
import com.app.group15.passwordPolicyManagement.PasswordPolicy;
import com.app.group15.passwordPolicyManagement.PasswordPolicyAbstractDao;

import java.util.List;

public class PasswordPolicyMinUpperCaseMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int minimumNumberOfUppercaseAllowed = 2;

		int countUppercase = 0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				countUppercase++;
			}
		}

		if (countUppercase >= minimumNumberOfUppercaseAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
