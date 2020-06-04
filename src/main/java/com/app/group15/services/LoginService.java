package com.app.group15.services;

import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserDao;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.model.User;

public class LoginService {
    private UserAbstractDao userDao = new UserDaoInjectorService().getUserDao();

    public boolean validateLogin(String bannerId, String password)
    {
        User user = userDao.getUserByBannerId(bannerId);
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
