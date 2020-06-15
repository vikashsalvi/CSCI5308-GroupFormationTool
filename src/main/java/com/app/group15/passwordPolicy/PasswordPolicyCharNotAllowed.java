package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.config.ServiceConfig;
import com.app.group15.dao.PasswordPolicyAbstractDao;
import com.app.group15.dao.PasswordPolicyDao;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.model.PasswordPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordPolicyCharNotAllowed implements IPasswordPolicyValidator{

	@Override
	public boolean isPasswordValid(String password) {

		PasswordPolicyAbstractDao passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
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
