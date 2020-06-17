package com.app.group15.passwordPolicyManagement;

import com.app.group15.config.AppConfig;

import java.util.List;

public class PasswordPolicyMinUpperCase implements IPasswordPolicyValidator{

	PasswordPolicyAbstractDao passwordPolicyDao;
	@Override
	public boolean isPasswordValid(String password) {

		passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		int minimumNumberOfUppercaseAllowed = Integer.parseInt(passwordPolicyList.get(2).getPolicyValue());

		int countUppercase = 0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				countUppercase++;
			}
		}

		if (countUppercase >= minimumNumberOfUppercaseAllowed) {
			return true;
		} else {
			return false;
		}
	}

}
