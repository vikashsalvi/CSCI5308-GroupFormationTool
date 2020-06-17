package com.app.group15.passwordPolicyManagement;

import java.util.ArrayList;
import java.util.List;

import com.app.group15.config.AppConfig;

public class PasswordPolicyHistoryConstraint implements IPasswordPolicyValidator {
	
	private UserPasswordHistoryAbstractDao passwordHistoryDao=AppConfig.getInstance().getUserPasswordHistoryDao();
	private int userId;
	
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public boolean isPasswordValid(String password) {
		List<UserPasswordHistory> previousPasswords=passwordHistoryDao.getPasswordHistory(this.userId);
		List<String> historyPasswords=new ArrayList<>();
		previousPasswords.forEach((pass) -> historyPasswords.add(pass.getHistoryPassword())); 
		if(historyPasswords.contains(password)) {
			return false;
		}
			return true;
	}

}
