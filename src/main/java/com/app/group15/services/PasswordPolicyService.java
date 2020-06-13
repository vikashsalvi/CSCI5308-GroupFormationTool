package com.app.group15.services;

import com.app.group15.injectors.service.IPasswordPolicyServiceInjector;
import com.app.group15.model.Policy;
import com.app.group15.passwordPolicy.IPasswordPolicyValidator;

import java.util.ArrayList;

public class PasswordPolicyService implements IPasswordPolicyServiceInjector,IPasswordPolicyService {

	private IPasswordPolicyValidator passwordPolicy;

	@Override
	public void injectPasswordPolicy(IPasswordPolicyValidator passwordPolicy) {
		this.passwordPolicy=passwordPolicy;
		
	}
	@Override
	public boolean validatePassword(String password) {
		return passwordPolicy.isPasswordValid(password);
		
	}

	// This is temp, and will be removed
	public ArrayList<Policy> populateList() {
		ArrayList<Policy> policyArrayList = new ArrayList<>();

		Policy policy = new Policy();

		policy.setId(1);
		policy.setPolicyState(true);
		policy.setPolicyName("Minimum length");
		policy.setPolicyValue("8");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(2);
		policy.setPolicyState(true);
		policy.setPolicyName("Maximum length");
		policy.setPolicyValue("16");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(3);
		policy.setPolicyState(false);
		policy.setPolicyName("Minimum number of uppercase characters");
		policy.setPolicyValue("2");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(4);
		policy.setPolicyState(false);
		policy.setPolicyName("Minimum number of lowercase characters");
		policy.setPolicyValue("10");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(5);
		policy.setPolicyState(true);
		policy.setPolicyName("Minimum number of symbols or special characters");
		policy.setPolicyValue("1");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(6);
		policy.setPolicyState(true);
		policy.setPolicyName("A set of characters that are not allowed");
		policy.setPolicyValue("$ * & %");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(7);
		policy.setPolicyState(false);
		policy.setPolicyName("Password history constraint");
		policy.setPolicyValue("3");
		policyArrayList.add(policy);

		return policyArrayList;

	}



}
