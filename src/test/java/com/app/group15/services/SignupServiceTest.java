package com.app.group15.services;

import com.app.group15.persistence.dao.UserDaoMock;
import com.app.group15.userManagement.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

class SignupServiceTest {

	private UserDaoMock userDaoMock = new UserDaoMock();

	@Test
	public void checkUserExistsTest() {
		User user = userDaoMock.getUserByBannerIdMock("B00843468");
		assertEquals(user.getFirstName(), "Daksh");

		user = userDaoMock.getUserByBannerIdMock("B00843469");
		assertNull(user.getFirstName());
	}

	@Test
	public void createUserTest() {
		User user = new User();
		int userId = userDaoMock.saveUserMock(user, "GUEST");
		assertEquals(userId, 0);
	}
	
	
}
