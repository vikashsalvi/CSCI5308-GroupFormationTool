package com.app.group15.injectors.dao;

import com.app.group15.dao.PasswordPolicyDao;

public class PasswordPolicyDaoInjector {

    private PasswordPolicyDao passwordPolicyDao;

    public PasswordPolicyDaoInjector() {
        passwordPolicyDao = new PasswordPolicyDao();
    }

    public PasswordPolicyDao getPasswordPolicyDao() {
        return passwordPolicyDao;
    }

}
