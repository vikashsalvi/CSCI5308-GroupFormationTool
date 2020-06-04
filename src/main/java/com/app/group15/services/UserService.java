package com.app.group15.services;


import com.app.group15.dao.UserDao;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.injectors.UserRoleDaoInjectorService;
import com.app.group15.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserService {
	private static UserDao userDao = new UserDaoInjectorService().getUserDao();
	private static UserRoleDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();

	public static ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		users = userDao.getAll();
//		Set<String> allowedRoles = new HashSet<>(Arrays.asList("GUEST", "INSTRUCTOR"));

		users.removeIf(
			user -> user.getBannerId().equals("admin") ||
				userRoleDao.getRolesByBannerId(user.getBannerId()).contains("STUDENT") ||
				userRoleDao.getRolesByBannerId(user.getBannerId()).contains("TA"));
		return users;
	}

	public static void updateUserRole(int userId, String role) {
		userRoleDao.updateRole(userId, role);
	}

}
