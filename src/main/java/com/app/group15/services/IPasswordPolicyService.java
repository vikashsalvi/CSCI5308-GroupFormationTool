package com.app.group15.services;

import com.app.group15.model.PasswordPolicy;

import java.util.List;

public interface IPasswordPolicyService {
	
	public boolean validatePassword(String password);

	public List<PasswordPolicy> getAllPolicy();
	public void updatePolicy(String policyID, int policyState, String policyValue);

}
