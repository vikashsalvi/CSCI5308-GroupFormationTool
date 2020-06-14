package com.app.group15.passwordPolicy;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserPasswordHistoryAbstractDao;

public class PasswordPolicyHistoryConstraint implements IPasswordPolicyValidator {
	
	private UserPasswordHistoryAbstractDao passwordHistoryDao=AppConfig.getInstance().getUserPasswordHistoryDao();
	
	@Override
	public boolean isPasswordValid(String password) {
		
		
		return false;
	}

}
