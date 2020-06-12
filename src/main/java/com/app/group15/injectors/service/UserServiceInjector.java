package com.app.group15.injectors.service;

import com.app.group15.dao.UserRoleDao;
import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.services.UserService;

public class UserServiceInjector {
	
	private UserService userService;
	
	public UserServiceInjector() {
		userService=new UserService();
		userService.injectUserDao(new UserDaoInjectorService().getUserDao());
		userService.injectUserRoleDao(new UserRoleDao());
	}

	public UserService getUserService() {
		return userService;
	}
	
	

}
