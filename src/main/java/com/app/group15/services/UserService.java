package com.app.group15.services;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.dao.UserRoleDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import com.app.group15.persistence.injectors.UserRoleDaoInjectorService;

import java.util.ArrayList;

public class UserService {
	private static UserDao userDao = new UserDaoInjectorService().getUserDao();
	private static UserRoleDao userRoleDao=new UserRoleDaoInjectorService().getUserRoleDao();
	public static ArrayList<UserEntity> getAllUsers() {
		ArrayList<UserEntity> userEntities = new ArrayList<>();
		userEntities = userDao.getAll();
		userEntities.removeIf(userEntity -> userEntity.getBannerId().equals("admin"));
		return userEntities;
	}
	public static void updateUserRole(int userId, String role){
		userRoleDao.updateRole(userId, role);
	}

}
