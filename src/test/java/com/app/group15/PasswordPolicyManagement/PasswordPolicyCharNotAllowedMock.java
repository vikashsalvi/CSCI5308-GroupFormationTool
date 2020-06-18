package com.app.group15.PasswordPolicyManagement;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCharNotAllowedMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		String bannedCharString = "$#*";
		List<String> bannedCharList = new ArrayList<>();

		bannedCharString = bannedCharString.replaceAll(" +", "");

		for (int i = 0; i < bannedCharString.length(); i++) {
			bannedCharList.add(String.valueOf(bannedCharString.charAt(i)));
		}

		for (String s : bannedCharList) {
			if (password.contains(s)) {
				return false;
			}
		}

		return true;
	}

}
