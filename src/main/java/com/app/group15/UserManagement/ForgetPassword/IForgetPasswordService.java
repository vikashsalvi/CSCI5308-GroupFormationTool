package com.app.group15.UserManagement.ForgetPassword;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IForgetPasswordService {
    void generateToken(String bannerId, HttpServletRequest request);

    boolean verifyToken(String token);

    Map<String, String> getUserFromToken(String token);

    boolean updateUserPassword(int id, String newPassword);

    boolean deleteForgotPasswordToken(int id);
}
