package com.app.group15.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.app.group15.persistence.dao.UserDaoMock;
import com.app.group15.userManagement.User;


class UserServiceTest {
	
	private  UserDaoMock userDaoMock = new UserDaoMock();
	
	@Test
	public  void getAllUsersTest() {
		ArrayList<User> userList=userDaoMock.getUserList();
		userList.removeIf(user -> user.getBannerId().equals("admin"));
		assertEquals(userList.size(), 1);
		
	}
}
