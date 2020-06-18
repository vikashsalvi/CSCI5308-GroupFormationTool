package com.app.group15.PasswordPolicyManagement;

import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;
import com.app.group15.Config.AppConfig;

import java.util.List;
import java.util.logging.Level;

public class PasswordPolicyMinUpperCase implements IPasswordPolicyValidator {

	PasswordPolicyAbstractDao passwordPolicyDao;

	@Override
	public boolean isPasswordValid(String password) {
		if (ServiceUtility.isNotNull(password)) {
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
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Password is null");
		}
		return false;
	}

}
