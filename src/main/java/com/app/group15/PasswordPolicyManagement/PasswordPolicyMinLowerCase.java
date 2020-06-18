package com.app.group15.PasswordPolicyManagement;

import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;
import com.app.group15.Config.AppConfig;

import java.util.List;
import java.util.logging.Level;

public class PasswordPolicyMinLowerCase implements IPasswordPolicyValidator {

	private PasswordPolicyAbstractDao passwordPolicyDao;

	@Override
	public boolean isPasswordValid(String password) {
		if (ServiceUtility.isNotNull(password)) {
			passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
			List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();

			int minimumNumberOfLowercaseAllowed = Integer.parseInt(passwordPolicyList.get(3).getPolicyValue());
			int countLowercase = 0;

			for (int i = 0; i < password.length(); i++) {
				if (Character.isLowerCase(password.charAt(i))) {
					countLowercase++;
				}
			}

			if (countLowercase >= minimumNumberOfLowercaseAllowed) {
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
