package com.app.group15.persistence.dao;

import com.app.group15.persistence.entity.UserEntity;

public class UserDaoMock {

	public UserEntity getUserByBannerIdMock(String bannerId) {
		UserEntity userEntity = new UserEntity();
		if (bannerId.equals("B00843468")) {
			userEntity.setFirstName("Daksh");
			userEntity.setLastName("Patel");
			userEntity.setEmail("daksh.patel@dal.ca");
			userEntity.setBannerId("B00843468");
			return userEntity;
		} else {
			return userEntity;
		}
	}

	public int saveUserMock(UserEntity user, String role) {
		return 0;
	}
}
