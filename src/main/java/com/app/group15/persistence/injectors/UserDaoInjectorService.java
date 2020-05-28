package com.app.group15.persistence.injectors;

import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.dao.UserDao;


public class UserDaoInjectorService {
	
	private UserDao userDao;
	
	public UserDaoInjectorService() {
		userDao=new UserDao();
		userDao.injectConnection(DatabaseManager.getConnection());
		userDao.injectUserRoleDao(new UserRoleDaoInjectorService().getUserRoleDao());
	}

	public UserDao getUserDao() {
		return userDao;
	}

	
	

}
