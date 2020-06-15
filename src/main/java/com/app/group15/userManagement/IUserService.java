package com.app.group15.userManagement;

import java.util.List;

public interface IUserService {

	public  List<User> getAllUsers() ;
	public  void updateUserRole(int userId, String role);
	
}
