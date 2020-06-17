package com.app.group15.UserManagement.SignupManagement;

import java.util.logging.Level;

import com.app.group15.UserManagement.User;
import com.app.group15.UserManagement.UserAbstractDao;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;

public class SignupService implements ISignupService, ISignUpServiceInjector {
	private UserAbstractDao userDao;

	public boolean checkUserExists(String bannerId) {
		User user = userDao.getUserByBannerId(bannerId);
		boolean response;

		response = user.getBannerId() != null;
		return response;
	}

	public int createUser(User user, String role) {
		if (ServiceUtility.isNotNull(user) && ServiceUtility.isNotNull(role)) {
			return userDao.saveUser(user, role);
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid Input");
		}
		return -1;
	}

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		if (ServiceUtility.isNotNull(userDao)) {
			this.userDao = userDao;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "User Dao Dependency not injected ");
		}

	}

}
