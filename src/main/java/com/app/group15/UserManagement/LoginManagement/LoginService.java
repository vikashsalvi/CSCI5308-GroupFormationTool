package com.app.group15.UserManagement.LoginManagement;

import java.util.logging.Level;

import com.app.group15.UserManagement.User;
import com.app.group15.UserManagement.UserAbstractDao;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;

public class LoginService implements ILoginService, ILoginServiceInjector {
	private UserAbstractDao userDao;

	public boolean validateLogin(String bannerId, String password) {
		User user = userDao.getUserByBannerId(bannerId);
		if (user.getBannerId() != null && user.getPassword() != null) {
			if (bannerId.equals(user.getBannerId()) && password.equals(user.getPassword())) {

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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