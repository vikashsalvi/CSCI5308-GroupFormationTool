package com.app.group15.services;

import com.app.group15.model.PasswordPolicy;
import com.app.group15.model.PasswordPolicyValidationResult;

import java.util.List;

public interface IPasswordPolicyService {
	
	public PasswordPolicyValidationResult validatePassword(String password,int userId);

	public List<PasswordPolicy> getAllPolicy();
	

}
