package com.app.group15.services;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;

public class SignupService {
	private UserDao userDao = new UserDaoInjectorService().getUserDao();

	public boolean checkUserExists(String bannerId) {
		UserEntity user = userDao.getUserByBannerId(bannerId);
		boolean response;

		if (user.getBannerId() == null) {
			response = false;
		} else {
			response = true;
		}
		return response;
	}

	public int createUser(UserEntity user, String role) {
		int userId = userDao.saveUser(user, role);
		return userId;
	}
}
