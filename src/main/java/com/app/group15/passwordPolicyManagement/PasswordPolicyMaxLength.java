package com.app.group15.passwordPolicyManagement;

import com.app.group15.config.AppConfig;

import java.util.List;

public class PasswordPolicyMaxLength implements IPasswordPolicyValidator{

	@Override
	public boolean isPasswordValid(String password) {

		PasswordPolicyAbstractDao passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		int maximumLengthAllowed = Integer.parseInt(passwordPolicyList.get(1).getPolicyValue());

		if (password.length() <= maximumLengthAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
