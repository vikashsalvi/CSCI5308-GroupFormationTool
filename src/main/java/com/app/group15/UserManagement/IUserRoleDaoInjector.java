package com.app.group15.UserManagement;


import com.app.group15.PasswordPolicyManagement.UserPasswordHistoryAbstractDao;
import com.app.group15.Persistence.IDao;

public interface IUserRoleDaoInjector {

	public void injectUserRoleDao(@SuppressWarnings("rawtypes") IDao userRoleDao);
	public void injectPasswordHistoryDao(UserPasswordHistoryAbstractDao passwordHistoryDao);

}
