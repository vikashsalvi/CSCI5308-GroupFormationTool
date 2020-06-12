package com.app.group15.services;

import com.app.group15.injectors.service.IPasswordPolicyServiceInjector;
import com.app.group15.passwordPolicy.IPasswordPolicyValidator;

public class PasswordPolicyService implements IPasswordPolicyServiceInjector,IPasswordPolicyService {

	private IPasswordPolicyValidator passwordPolicy;
	@Override
	public void injectPasswordPolicy(IPasswordPolicyValidator passwordPolicy) {
		this.passwordPolicy=passwordPolicy;
		
	}
	@Override
	public boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		passwordPolicy.isPasswordValid(password);
		return false;
	}

}
