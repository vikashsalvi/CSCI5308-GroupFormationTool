package com.app.group15.UserManagement.ForgetPassword;

import com.app.group15.UserManagement.UserDao;

public interface IForgetPasswordServiceInjector {

    public void injectUserDao(UserDao userDao);

    public void injectForgetPasswordDao(ForgetPasswordAbstractDao forgetPasswordDao);
}
