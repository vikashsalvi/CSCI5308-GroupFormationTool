package com.app.group15.persistence.dao;

import com.app.group15.model.User;

public class UserDaoMock {

	public User getUserByBannerIdMock(String bannerId) {
		User user = new User();
		if (bannerId.equals("B00843468")) {
			user.setFirstName("Daksh");
			user.setLastName("Patel");
			user.setEmail("daksh.patel@dal.ca");
			user.setBannerId("B00843468");
			user.setBannerId("passwordTest");
			return user;
		} else {
			return user;
		}
	}

	public int saveUserMock(User user, String role) {
		return 0;
	}
}
