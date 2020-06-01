package com.app.group15.injectors;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserDao;



public class UserDaoInjectorService {
	
	private UserDao userDao;
	
	public UserDaoInjectorService() {

		userDao = AppConfig.getInstance().getUserDao();
		userDao.injectUserRoleDao(AppConfig.getInstance().getUserRoleDaoInjectorService().getUserRoleDao());
	}

	public UserDao getUserDao() {
		return userDao;
	}

	
	

}
