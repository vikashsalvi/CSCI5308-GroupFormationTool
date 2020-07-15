package com.app.group15.UserManagement;

import com.app.group15.Config.AppConfig;

public class UserRoleDaoInjectorService {

	private UserRoleDao userRoleDao;
	private IUserManagementAbstractFactory userManagementAbstractFactory = AppConfig.getInstance().getUserManagementAbstractFactory();

	public UserRoleDaoInjectorService() {
		userRoleDao = new UserRoleDao();
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

}
