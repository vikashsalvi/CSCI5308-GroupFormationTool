package com.app.group15.UserManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;

public class UserService implements IUserService, IUserServiceInjector {

	private UserAbstractDao userDao;
	private UserRoleAbstractDao userRoleDao;

	public List<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		users = userDao.getAll();
		users.removeIf(user -> user.getBannerId().equals("admin")
				|| userRoleDao.getRolesByBannerId(user.getBannerId()).contains("STUDENT")
				|| userRoleDao.getRolesByBannerId(user.getBannerId()).contains("TA"));
		return users;
	}

	public void updateUserRole(int userId, String role) {
		if (ServiceUtility.isNotNull(role) && ServiceUtility.isValidInt(userId)) {
			userRoleDao.updateRole(userId, role);
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid Input");
		}
	}

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		if (ServiceUtility.isNotNull(userDao)) {
			this.userDao = userDao;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "user dao not injected");
		}

	}

	@Override
	public void injectUserRoleDao(UserRoleAbstractDao userRoleAbstractDao) {
		if (ServiceUtility.isNotNull(userRoleAbstractDao)) {
			this.userRoleDao = userRoleAbstractDao;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, " userRoleAbstractDao not injected");
		}

	}

	@Override
	public boolean validateBannerID(String bannerId) {
		if (ServiceUtility.isNotNull(bannerId)) {
			Set<String> roleSet = userRoleDao.getRolesByBannerId(bannerId);
			if (roleSet.contains("STUDENT")) {
				return true;
			} else {
				return false;
			}
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Bnaner id is null");
		}
		return false;
	}

}
