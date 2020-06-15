package com.app.group15.passwordPolicyManagement;

import com.app.group15.config.AppConfig;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyService implements IPasswordPolicyServiceInjector, IPasswordPolicyService {

	private List<IPasswordPolicyValidator> passwordPolicyList;

	@Override
	public void injectPasswordPolicy(List<IPasswordPolicyValidator> passwordPolicy) {
		this.passwordPolicyList = passwordPolicy;

	}

	@Override
	public PasswordPolicyValidationResult validatePassword(String password, int userId) {

		PasswordPolicyValidationResult result = new PasswordPolicyValidationResult();
		List<String> failList = new ArrayList<String>();

		for (int i = 0; i < passwordPolicyList.size(); i++) {
			if (passwordPolicyList.get(i).getClass().getSimpleName().equals("PasswordPolicyHistoryConstraint")) {
				PasswordPolicyHistoryConstraint passwordPolicy = (PasswordPolicyHistoryConstraint) passwordPolicyList
						.get(i);
				passwordPolicy.setUserId(userId);
				passwordPolicyList.set(i, passwordPolicy);
				
			}
			if (passwordPolicyList.get(i).isPasswordValid(password)) {
				continue;
			} else {
				failList.add(passwordPolicyList.get(i).getClass().getSimpleName());

			}
		}
		if (failList.size() > 0) {
			result.setFailedPolicyList(failList);
			result.setResult(false);
		} else {
			result.setResult(true);
		}
		return result;
	}

	@Override
	public List<PasswordPolicy> getAllPolicy() {
		PasswordPolicyAbstractDao passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();
		return passwordPolicyList;
	}

}
