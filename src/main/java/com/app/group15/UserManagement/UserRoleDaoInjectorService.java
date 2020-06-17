package com.app.group15.UserManagement;

public class UserRoleDaoInjectorService {

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	private UserRoleDao userRoleDao;

	public UserRoleDaoInjectorService() {
		userRoleDao = new UserRoleDao();

	}

}
