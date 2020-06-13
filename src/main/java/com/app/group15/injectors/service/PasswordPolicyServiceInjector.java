package com.app.group15.injectors.service;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.PasswordPolicyDao;
import com.app.group15.model.PasswordPolicy;
import com.app.group15.passwordPolicy.*;
import com.app.group15.services.PasswordPolicyService;

import java.util.List;

public class PasswordPolicyServiceInjector {

	private PasswordPolicyDao passwordPolicyDao;
	private PasswordPolicyService passwordPolicyService;

	public PasswordPolicyServiceInjector() {

		passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> activePasswordPolicy = passwordPolicyDao.getActivePasswordPolicy();
		passwordPolicyService = new PasswordPolicyService();

		activePasswordPolicy.forEach(policy -> {
			switch (policy.getPolicyId()) {
				case 1:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyMinLength());
					break;
				case 2:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyMaxLength());
					break;
				case 3:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyMinUpperCase());
					break;
				case 4:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyMinLowerCase());
					break;
				case 5:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyMinSpecialChar());
					break;
				case 6:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyCharNotAllowed());
					break;
				case 7:
					passwordPolicyService.injectPasswordPolicy(new PasswordPolicyHistoryConstraint());
					break;
			}
		});
	}

	public PasswordPolicyService getPasswordPolicyService() {
		return passwordPolicyService;
	}
}
