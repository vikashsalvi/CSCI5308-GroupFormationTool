package com.app.group15.UserManagement;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    void updateUserRole(int userId, String role);

    boolean validateBannerID(String bannerId);

}
