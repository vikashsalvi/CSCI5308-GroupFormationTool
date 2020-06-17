package com.app.group15.UserManagement.ForgetPassword;

import com.app.group15.PasswordPolicyManagement.UserPasswordHistoryAbstractDao;

public interface IForgetPasswordDaoInjector {


    public void injectPasswordHistoryDao(UserPasswordHistoryAbstractDao passwordHistoryDao);

}
