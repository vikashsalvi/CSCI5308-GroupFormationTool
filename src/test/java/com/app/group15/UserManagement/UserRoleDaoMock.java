package com.app.group15.UserManagement;

import java.util.HashSet;
import java.util.Set;


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
