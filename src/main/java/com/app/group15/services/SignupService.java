package com.app.group15.services;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;

public class SignupService {
	private UserDao userDao = new UserDaoInjectorService().getUserDao();

	public boolean checkUserExists(String bannerId) {
		UserEntity user = userDao.getUserByBannerId(bannerId);
		boolean response;

		response = user.getBannerId() != null;
		return response;
	}

	public int createUser(UserEntity user, String role) {
		return userDao.saveUser(user, role);
	}
}
