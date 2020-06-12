package com.app.group15.injectors.service;

import com.app.group15.dao.UserAbstractDao;

public interface ISignUpServiceInjector {
	public void injectUserDao(UserAbstractDao userDao);
}
