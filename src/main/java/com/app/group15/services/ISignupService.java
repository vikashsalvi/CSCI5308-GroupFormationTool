package com.app.group15.services;

import com.app.group15.model.User;

public interface ISignupService {
	public boolean checkUserExists(String bannerId);
	public int createUser(User user, String role);
}
