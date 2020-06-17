package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyValidator;
import com.app.group15.passwordPolicyManagement.PasswordPolicy;
import com.app.group15.passwordPolicyManagement.PasswordPolicyAbstractDao;

import java.util.List;

public class PasswordPolicyMinSpecialCharMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int minimumNumberOfSpecialCharAllowed = 2;

		int countSpecialChar=0;

		for (int i =0; i < password.length(); i++ )
		{
			char c = password.charAt(i);

			if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c))
			{
				countSpecialChar++;
			}
		}

		if (countSpecialChar >= minimumNumberOfSpecialCharAllowed){
			return true;
		}else {
			return false;
		}
	}

}
