package com.app.group15.PasswordPolicyManagement;

public interface IPasswordPolicyService {
	
	public PasswordPolicyValidationResult validatePassword(String password,int userId);

	

}
