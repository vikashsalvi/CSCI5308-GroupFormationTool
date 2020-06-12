package com.app.group15.services;

import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserDao;
import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.injectors.service.ISignUpServiceInjector;
import com.app.group15.model.User;

public class SignupService implements ISignupService,ISignUpServiceInjector{
	private UserAbstractDao userDao ;

	public boolean checkUserExists(String bannerId) {
		User user = userDao.getUserByBannerId(bannerId);
		boolean response;

		response = user.getBannerId() != null;
		return response;
	}

	public int createUser(User user, String role) {
		return userDao.saveUser(user, role);
	}

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		this.userDao=userDao;
		
	}
	
	
}
