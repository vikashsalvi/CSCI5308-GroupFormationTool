package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.PasswordPolicyAbstractDao;
import com.app.group15.dao.PasswordPolicyDao;
import com.app.group15.model.PasswordPolicy;

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
