package com.app.group15.PasswordPolicyManagement;

public class PasswordPolicyMinSpecialCharMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		int minimumNumberOfSpecialCharAllowed = 2;

		int countSpecialChar = 0;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);

			if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c)) {
				countSpecialChar++;
			}
		}

		return countSpecialChar >= minimumNumberOfSpecialCharAllowed;
	}

}
