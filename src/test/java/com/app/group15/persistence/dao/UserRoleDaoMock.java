package com.app.group15.persistence.dao;

import com.app.group15.ExceptionHandler.AdminNotAllowedException;
import com.app.group15.persistence.DatabaseManager;
import com.app.group15.userManagement.UserRole;
import com.app.group15.userManagement.UserRoleAbstractDao;
import com.app.group15.userManagement.UserRoles;
import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;


@SuppressWarnings("rawtypes")
public class UserRoleDaoMock {

	public UserRoleDaoMock() {

	}

	public Set<String> getRolesByBannerIdMock(String bannerId) {
		Set<String> roles = new HashSet<String>();
		switch (bannerId) {
			case "admin":
				roles.add("ADMIN");
				break;
			case "B00843800":
				roles.add("INSTRUCTOR");
				break;
			case "B00843468":
			case "B00843467":
				roles.add("STUDENT");
				break;
			default:
				roles.add("GUEST");
				break;
		}
		return roles;
	}


}
