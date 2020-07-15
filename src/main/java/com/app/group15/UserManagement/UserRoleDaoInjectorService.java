package com.app.group15.UserManagement;

import com.app.group15.Config.AppConfig;

public class UserRoleDaoInjectorService {

    private UserRoleAbstractDao userRoleDao;

    public UserRoleDaoInjectorService() {
        userRoleDao = AppConfig.getInstance().getUserManagementAbstractFactory().getUserRoleDao();

    }

    public UserRoleAbstractDao getUserRoleDao() {
        return userRoleDao;
    }

}
