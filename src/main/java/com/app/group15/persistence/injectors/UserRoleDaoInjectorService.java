package com.app.group15.persistence.injectors;



import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.dao.UserRoleDao;

public class UserRoleDaoInjectorService {
	
	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}


	private UserRoleDao userRoleDao;
	
	public UserRoleDaoInjectorService() {
		userRoleDao=new UserRoleDao();
		userRoleDao.injectConnection(DatabaseManager.getConnection());
	}
	
	
	
	
	

}
