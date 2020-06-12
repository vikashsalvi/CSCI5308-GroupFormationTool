package com.app.group15.services;

import java.util.List;

import com.app.group15.model.User;

public interface IUserService {

	public  List<User> getAllUsers() ;
	public  void updateUserRole(int userId, String role);
	
}
