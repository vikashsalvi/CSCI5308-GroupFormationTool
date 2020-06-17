package com.app.group15.PasswordPolicyManagement;

import com.app.group15.config.AppConfig;

import java.util.List;

public class PasswordPolicyMaxLength implements IPasswordPolicyValidator{

	private PasswordPolicyAbstractDao passwordPolicyDao;

	@Override
	public boolean isPasswordValid(String password) {

		passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();

		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		int maximumLengthAllowed = Integer.parseInt(passwordPolicyList.get(1).getPolicyValue());

		if (password.length() <= maximumLengthAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
