package com.app.group15.UserManagement;

public class UserDaoInjectorService {
	
	private UserDao userDao;
	
	public UserDaoInjectorService() {

		userDao = new UserDao();
		userDao.injectUserRoleDao(new UserRoleDao());
	}

	public UserDao getUserDao() {
		return userDao;
	}

	
	

}
