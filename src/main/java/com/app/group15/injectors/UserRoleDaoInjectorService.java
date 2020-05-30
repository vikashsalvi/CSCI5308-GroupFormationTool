package com.app.group15.injectors;



import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.persistence.DatabaseManager;

public class UserRoleDaoInjectorService {
	
	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}


	private UserRoleDao userRoleDao;
	
	public UserRoleDaoInjectorService() {
		userRoleDao=new UserRoleDao();
		userRoleDao.injectConnection(AppConfig.getInstance().getDatabaseManager().getConnection());
	}
	
	
	
	
	

}
