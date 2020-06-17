package com.app.group15.UserManagement.LoginManagement;

import com.app.group15.UserManagement.UserAbstractDao;

public interface ILoginServiceInjector {

	public void injectUserDao(UserAbstractDao userDao);
}
