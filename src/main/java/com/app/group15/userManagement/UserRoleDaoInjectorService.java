package com.app.group15.userManagement;

public class UserRoleDaoInjectorService {

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	private UserRoleDao userRoleDao;

	public UserRoleDaoInjectorService() {
		userRoleDao = new UserRoleDao();

	}

}
