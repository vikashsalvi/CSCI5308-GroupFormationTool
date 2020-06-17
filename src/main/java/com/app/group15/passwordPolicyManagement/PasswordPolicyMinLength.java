package com.app.group15.passwordPolicyManagement;

import com.app.group15.config.AppConfig;

import java.util.List;

public class PasswordPolicyMinLength implements IPasswordPolicyValidator{

	@Override
	public boolean isPasswordValid(String password) {

		PasswordPolicyAbstractDao passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		int minimumLengthAllowed = Integer.parseInt(passwordPolicyList.get(0).getPolicyValue());



		if (password.length() >= minimumLengthAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
