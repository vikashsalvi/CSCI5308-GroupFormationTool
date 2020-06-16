package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyValidator;
import com.app.group15.passwordPolicyManagement.PasswordPolicy;
import com.app.group15.passwordPolicyManagement.PasswordPolicyAbstractDao;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCharNotAllowedMock implements IPasswordPolicyValidator {

	@Override
	public boolean isPasswordValid(String password) {

		String bannedCharString = "$#*";
		List<String> bannedCharList = new ArrayList<>();

		bannedCharString.replaceAll(" +","");

		for (int i=0; i<bannedCharString.length();i++)
		{
			bannedCharList.add(String.valueOf(bannedCharString.charAt(i)));
		}

		for (int i=0; i< bannedCharList.size(); i++)
		{
			if (password.contains(bannedCharList.get(i)))
			{
				return false;
			}
		}

		return true;
	}

}
