package com.app.group15.injectors.service;

import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserRoleAbstractDao;

public interface IUserServiceInjector {
	public void injectUserDao(UserAbstractDao userDao);
	public void injectUserRoleDao( UserRoleAbstractDao userRoleAbstractDao);
}
