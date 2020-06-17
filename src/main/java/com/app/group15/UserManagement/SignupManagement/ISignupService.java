package com.app.group15.UserManagement.SignupManagement;

import com.app.group15.UserManagement.User;

public interface ISignupService {
    public boolean checkUserExists(String bannerId);

    public int createUser(User user, String role);
}
