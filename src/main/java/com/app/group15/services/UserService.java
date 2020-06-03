package com.app.group15.services;


import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserDao;
import com.app.group15.dao.UserRoleAbstractDao;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.injectors.UserRoleDaoInjectorService;
import com.app.group15.model.User;

import java.util.ArrayList;

public class UserService {
	private static UserAbstractDao userDao = new UserDaoInjectorService().getUserDao();
	private static UserRoleAbstractDao userRoleDao=new UserRoleDaoInjectorService().getUserRoleDao();
	public static ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		users = userDao.getAll();
		users.removeIf(user -> user.getBannerId().equals("admin"));
		return users;
	}
	public static void updateUserRole(int userId, String role){
		userRoleDao.updateRole(userId, role);
	}

}
