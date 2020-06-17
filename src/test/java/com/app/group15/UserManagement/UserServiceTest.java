package com.app.group15.UserManagement;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserServiceTest {
	
	private  UserDaoMock userDaoMock = new UserDaoMock();
	
	@Test
	public  void getAllUsersTest() {
		ArrayList<User> userList=userDaoMock.getUserList();
		userList.removeIf(user -> user.getBannerId().equals("admin"));
		assertEquals(userList.size(), 1);
		
	}
}
