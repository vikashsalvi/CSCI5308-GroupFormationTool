package com.app.group15.userManagement;


import java.util.ArrayList;

import java.util.List;
import java.util.Set;


public class UserService implements IUserService,IUserServiceInjector {

	private UserAbstractDao userDao ;
	private UserRoleAbstractDao userRoleDao;


	public  List<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		users = userDao.getAll();
//		Set<String> allowedRoles = new HashSet<>(Arrays.asList("GUEST", "INSTRUCTOR"));

		users.removeIf(
			user -> user.getBannerId().equals("admin") ||
				userRoleDao.getRolesByBannerId(user.getBannerId()).contains("STUDENT") ||
				userRoleDao.getRolesByBannerId(user.getBannerId()).contains("TA"));
		return users;
	}

	public  void updateUserRole(int userId, String role) {
		userRoleDao.updateRole(userId, role);
	}

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		this.userDao=userDao;
		
	}

	@Override
	public void injectUserRoleDao(UserRoleAbstractDao userRoleAbstractDao) {
		this.userRoleDao=userRoleAbstractDao;
		
	}

	@Override
	public boolean validateBannerID(String bannerId) {
		Set<String> roleSet = userRoleDao.getRolesByBannerId(bannerId);
		if (roleSet.contains("STUDENT")) {
			return true;
		}else {
			return false;
		}
	}

}
