package com.app.group15.userManagement;

public interface ISignupService {
	public boolean checkUserExists(String bannerId);
	public int createUser(User user, String role);
}
