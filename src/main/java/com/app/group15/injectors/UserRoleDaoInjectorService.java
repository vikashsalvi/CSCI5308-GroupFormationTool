package com.app.group15.injectors;

import com.app.group15.dao.UserRoleDao;

public class UserRoleDaoInjectorService {

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	private UserRoleDao userRoleDao;

	public UserRoleDaoInjectorService() {
		userRoleDao = new UserRoleDao();

	}

}
