package com.app.group15.services;

import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserDao;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.model.User;

public class SignupService {
	private UserAbstractDao userDao = new UserDaoInjectorService().getUserDao();

	public boolean checkUserExists(String bannerId) {
		User user = userDao.getUserByBannerId(bannerId);
		boolean response;

		response = user.getBannerId() != null;
		return response;
	}

	public int createUser(User user, String role) {
		return userDao.saveUser(user, role);
	}
}
