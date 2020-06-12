package com.app.group15.injectors.service;

import com.app.group15.passwordPolicy.IPasswordPolicyValidator;

public interface IPasswordPolicyServiceInjector {
	public void injectPasswordPolicy(IPasswordPolicyValidator passwordPolicy) ;

}
