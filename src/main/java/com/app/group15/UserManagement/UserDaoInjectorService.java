package com.app.group15.UserManagement;

import com.app.group15.Config.AppConfig;

public class UserDaoInjectorService {

	private UserAbstractDao userDao;

	public UserDaoInjectorService() {

		userDao = AppConfig.getInstance().getUserManagementAbstractFactory().getUserDao();
		userDao.injectUserRoleDao(AppConfig.getInstance().getUserManagementAbstractFactory().getUserRoleDao());
	}

	public UserAbstractDao getUserDao() {
		return userDao;
	}

}
