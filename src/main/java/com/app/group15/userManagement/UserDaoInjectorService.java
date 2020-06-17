package com.app.group15.userManagement;

import com.app.group15.passwordPolicyManagement.UserPasswordHistoryDao;

public class UserDaoInjectorService {
	
	private UserDao userDao;
	
	public UserDaoInjectorService() {

		userDao = new UserDao();
		userDao.injectUserRoleDao(new UserRoleDao());
		userDao.injectPasswordHistoryDao(new UserPasswordHistoryDao());
	}

	public UserDao getUserDao() {
		return userDao;
	}

	
	

}
