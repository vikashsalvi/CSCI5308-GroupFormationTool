package com.app.group15.injectors;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserDao;
import com.app.group15.persistence.DatabaseManager;


public class UserDaoInjectorService {
	
	private UserDao userDao;
	
	public UserDaoInjectorService() {

		userDao = AppConfig.instance().getUserDao();
		userDao.injectConnection(DatabaseManager.getConnection());
		userDao.injectUserRoleDao(AppConfig.instance().getUserRoleDaoInjectorService().getUserRoleDao());
	}

	public UserDao getUserDao() {
		return userDao;
	}

	
	

}
