package com.app.group15.injectors.service;

import java.util.List;

import com.app.group15.passwordPolicy.IPasswordPolicyValidator;

public interface IPasswordPolicyServiceInjector {
	public void injectPasswordPolicy(List<IPasswordPolicyValidator> passwordPolicyList) ;

}
