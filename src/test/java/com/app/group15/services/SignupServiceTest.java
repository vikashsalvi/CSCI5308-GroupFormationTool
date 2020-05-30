package com.app.group15.services;

import com.app.group15.persistence.dao.UserDaoMock;
import com.app.group15.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.jupiter.api.Assertions.*;

class SignupServiceTest {

	private UserDaoMock userDaoMock = new UserDaoMock();

	@Test
	public void checkUserExistsTest() {
		UserEntity userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
		assertEquals(userEntity.getFirstName(), "Daksh");

		userEntity = userDaoMock.getUserByBannerIdMock("B00843469");
		assertNull(userEntity.getFirstName());
	}

	@Test
	public void createUserTest() {
		UserEntity user = new UserEntity();
		int userId = userDaoMock.saveUserMock(user, "GUEST");
		assertEquals(userId, 0);
	}
}
