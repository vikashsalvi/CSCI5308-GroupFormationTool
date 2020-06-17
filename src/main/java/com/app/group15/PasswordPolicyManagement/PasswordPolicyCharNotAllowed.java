package com.app.group15.PasswordPolicyManagement;

import com.app.group15.config.AppConfig;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCharNotAllowed implements IPasswordPolicyValidator{

	private PasswordPolicyAbstractDao passwordPolicyDao;

	@Override
	public boolean isPasswordValid(String password) {

		passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		String bannedCharString = passwordPolicyList.get(5).getPolicyValue();
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
