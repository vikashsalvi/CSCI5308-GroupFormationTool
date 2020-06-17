package com.app.group15.passwordPolicy;

import com.app.group15.PasswordPolicyManagement.IPasswordPolicyValidator;

public class PasswordPolicyMinLowerCaseMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {


		int minimumNumberOfLowercaseAllowed = 2;

		int countLowercase =0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				countLowercase++;
			}
		}

		if (countLowercase >= minimumNumberOfLowercaseAllowed)
		{
			return true;
		}else {
			return false;
		}
	}

}
