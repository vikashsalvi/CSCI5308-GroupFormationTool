package com.app.group15.injectors.service;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.PasswordPolicyDao;
import com.app.group15.model.PasswordPolicy;
import com.app.group15.passwordPolicy.*;
import com.app.group15.services.PasswordPolicyService;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyServiceInjector {

	private PasswordPolicyDao passwordPolicyDao;
	private PasswordPolicyService passwordPolicyService;

	public PasswordPolicyServiceInjector() {

		passwordPolicyDao = new PasswordPolicyDao();
		List<PasswordPolicy> activePasswordPolicy = passwordPolicyDao.getActivePasswordPolicy();
		passwordPolicyService = new PasswordPolicyService();
		List<IPasswordPolicyValidator> activePolicyList=new ArrayList();

		activePasswordPolicy.forEach(policy -> {
			switch (policy.getPolicyId()) {
				case 1:
					activePolicyList.add(new PasswordPolicyMinLength());
					break;
				case 2:
					activePolicyList.add(new PasswordPolicyMaxLength());
					break;
				case 3:
					activePolicyList.add(new PasswordPolicyMinUpperCase());
					break;
				case 4:
					activePolicyList.add(new PasswordPolicyMinLowerCase());
					break;
				case 5:
					activePolicyList.add(new PasswordPolicyMinSpecialChar());
					break;
				case 6:
					activePolicyList.add(new PasswordPolicyCharNotAllowed());
					break;
				case 7:
					activePolicyList.add(new PasswordPolicyHistoryConstraint());
					break;
			}
		});
		passwordPolicyService.injectPasswordPolicy(activePolicyList);
	}

	public PasswordPolicyService getPasswordPolicyService() {
		return passwordPolicyService;
	}
}
