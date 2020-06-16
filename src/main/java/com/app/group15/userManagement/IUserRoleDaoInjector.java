package com.app.group15.userManagement;


import com.app.group15.passwordPolicyManagement.UserPasswordHistoryAbstractDao;
import com.app.group15.persistence.IDao;

public interface IUserRoleDaoInjector {

	public void injectUserRoleDao(@SuppressWarnings("rawtypes") IDao userRoleDao);
	public void injectPasswordHistoryDao(UserPasswordHistoryAbstractDao passwordHistoryDao);

}
