package com.app.group15.services;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.PasswordPolicyAbstractDao;
import com.app.group15.injectors.service.IPasswordPolicyServiceInjector;
import com.app.group15.model.PasswordPolicy;
import com.app.group15.model.PasswordPolicyValidationResult;
import com.app.group15.model.Policy;
import com.app.group15.passwordPolicy.IPasswordPolicyValidator;
import com.app.group15.passwordPolicy.PasswordPolicyHistoryConstraint;
import com.app.group15.persistence.InvokeStoredProcedure;

import java.sql.SQLException;
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
