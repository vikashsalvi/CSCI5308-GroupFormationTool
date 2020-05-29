package com.app.group15.services;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;

public class SignupService {
	private UserDao userDao = new UserDaoInjectorService().getUserDao();

	public boolean checkUserExists(String bannerId) {
		return true;
//		System.out.println("line 11");
//		UserEntity user = userDao.getUserByBannerId(bannerId);
//		boolean response;
//		System.out.print("line 13");
//		if (user.getBannerId() == null) {
//			response = false;
//			System.out.print(false);
//		} else {
//			response = true;
//			System.out.print(true);
//		}
//		System.out.println(response);
//		return response;
	}
}
