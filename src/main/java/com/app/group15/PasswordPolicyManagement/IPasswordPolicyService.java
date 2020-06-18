package com.app.group15.PasswordPolicyManagement;

public interface IPasswordPolicyService {

    PasswordPolicyValidationResult validatePassword(String password, int userId);


}
