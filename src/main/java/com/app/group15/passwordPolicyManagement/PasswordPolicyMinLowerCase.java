package com.app.group15.passwordPolicyManagement;

import com.app.group15.config.AppConfig;

import java.util.List;

public class PasswordPolicyMinLowerCase implements IPasswordPolicyValidator{

	private PasswordPolicyAbstractDao passwordPolicyDao;
	@Override
	public boolean isPasswordValid(String password) {

		passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

		int minimumNumberOfLowercaseAllowed = Integer.parseInt(passwordPolicyList.get(3).getPolicyValue());

		System.out.println("ID : " + passwordPolicyList.get(3).getPolicyId());
		System.out.println("Name : " + passwordPolicyList.get(3).getPolicyName());
		System.out.println("Value : " + minimumNumberOfLowercaseAllowed);

		int countLowercase =0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				countLowercase++;
			}
		}

		if (countLowercase >= minimumNumberOfLowercaseAllowed)
		{
			return true;
		}else {
			return false;
		}
	}

}
