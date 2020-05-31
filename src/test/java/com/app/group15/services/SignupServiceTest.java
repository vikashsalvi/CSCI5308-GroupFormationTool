package com.app.group15.services;

import com.app.group15.model.User;
import com.app.group15.persistence.dao.UserDaoMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SignupServiceTest {

	private UserDaoMock userDaoMock = new UserDaoMock();

	@Test
	public void checkUserExistsTest() {
		User userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
		assertEquals(userEntity.getFirstName(), "Daksh");

		userEntity = userDaoMock.getUserByBannerIdMock("B00843469");
		assertNull(userEntity.getFirstName());
	}

	@Test
	public void createUserTest() {
		User user = new User();
		int userId = userDaoMock.saveUserMock(user, "GUEST");
		assertEquals(userId, 0);
	}
}
