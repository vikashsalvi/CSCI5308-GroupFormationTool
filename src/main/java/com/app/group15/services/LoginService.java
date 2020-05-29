package com.app.group15.services;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;

public class LoginService {
    private UserDao userDao = new UserDaoInjectorService().getUserDao();

    public boolean validateLogin(String bannerId, String password)
    {
        UserEntity user = userDao.getUserByBannerId(bannerId);
        if (user.getBannerId() != null && user.getPassword()!=null)
        {
            if (bannerId.equals(user.getBannerId()) && password.equals(user.getPassword()))
            {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}
