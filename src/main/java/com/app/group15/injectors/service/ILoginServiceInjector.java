package com.app.group15.injectors.service;

import com.app.group15.dao.UserAbstractDao;

public interface ILoginServiceInjector {

	public void injectUserDao(UserAbstractDao userDao);
}
