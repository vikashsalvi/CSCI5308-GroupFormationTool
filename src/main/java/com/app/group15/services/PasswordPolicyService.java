package com.app.group15.services;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.PasswordPolicyAbstractDao;
import com.app.group15.injectors.service.IPasswordPolicyServiceInjector;
import com.app.group15.model.PasswordPolicy;
import com.app.group15.model.Policy;
import com.app.group15.passwordPolicy.IPasswordPolicyValidator;
import com.app.group15.persistence.InvokeStoredProcedure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<PasswordPolicy> getAllPolicy() {
		PasswordPolicyAbstractDao passwordPolicyDao = AppConfig.getInstance().getPasswordPolicyDao();
		List<PasswordPolicy> passwordPolicyList = passwordPolicyDao.getAll();
		return passwordPolicyList;
	}

	@Override
	public void updatePolicy(String policyID, int policyState, String policyValue)
	{
		InvokeStoredProcedure invokeStoredProcedure = null;
		try {
			invokeStoredProcedure = new InvokeStoredProcedure("spUpdatePolicy(?,?,?)");
			invokeStoredProcedure.setParameter(1,policyID);
			invokeStoredProcedure.setParameter(2,policyState);
			invokeStoredProcedure.setParameter(3,policyValue);
			invokeStoredProcedure.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			invokeStoredProcedure.closeConnection();
		}
	}

}
