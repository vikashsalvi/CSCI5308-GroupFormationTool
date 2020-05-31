package com.app.group15.persistence.dao;

import com.app.group15.model.User;

public class UserDaoMock {

	public User getUserByBannerIdMock(String bannerId) {
		User userEntity = new User();
		if (bannerId.equals("B00843468")) {
			userEntity.setFirstName("Daksh");
			userEntity.setLastName("Patel");
			userEntity.setEmail("daksh.patel@dal.ca");
			userEntity.setBannerId("B00843468");
			userEntity.setBannerId("passwordTest");
			return userEntity;
		} else {
			return userEntity;
		}
	}

	public int saveUserMock(User user, String role) {
		return 0;
	}
}
