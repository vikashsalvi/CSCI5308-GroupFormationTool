package com.app.group15.passwordPolicyManagement;

import java.util.List;

public interface IPasswordPolicyService {
	
	public PasswordPolicyValidationResult validatePassword(String password,int userId);

	public List<PasswordPolicy> getAllPolicy();
	

}
