package com.app.group15.PasswordPolicyManagement;

import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;
import com.app.group15.config.AppConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PasswordPolicyHistoryConstraint implements IPasswordPolicyValidator {

	private UserPasswordHistoryAbstractDao passwordHistoryDao = AppConfig.getInstance().getUserPasswordHistoryDao();
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean isPasswordValid(String password) {
		if (ServiceUtility.isValidInt(this.userId) && ServiceUtility.isNotNull(password)) {
			List<UserPasswordHistory> previousPasswords = passwordHistoryDao.getPasswordHistory(this.userId);
			List<String> historyPasswords = new ArrayList<>();
			previousPasswords.forEach((pass) -> historyPasswords.add(pass.getHistoryPassword()));
			if (historyPasswords.contains(password)) {
				return false;
			}
			return true;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Password is null or user id is invalid");
		}
		return false;
	}

}