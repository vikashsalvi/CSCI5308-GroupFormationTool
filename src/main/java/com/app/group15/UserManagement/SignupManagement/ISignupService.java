package com.app.group15.UserManagement.SignupManagement;

import com.app.group15.UserManagement.User;

public interface ISignupService {
    boolean checkUserExists(String bannerId);

    int createUser(User user, String role);
}
