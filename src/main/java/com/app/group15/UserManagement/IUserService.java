package com.app.group15.UserManagement;

import java.util.List;

public interface IUserService {

	public  List<User> getAllUsers() ;
	public  void updateUserRole(int userId, String role);
	public boolean validateBannerID(String bannerId);
	
}
