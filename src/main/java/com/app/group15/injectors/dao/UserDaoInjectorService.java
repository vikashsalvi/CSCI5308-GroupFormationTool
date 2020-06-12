package com.app.group15.injectors.dao;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserDao;
import com.app.group15.dao.UserRoleDao;



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
