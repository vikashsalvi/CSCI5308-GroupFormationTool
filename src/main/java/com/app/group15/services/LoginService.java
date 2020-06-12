package com.app.group15.services;

import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserDao;
import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.injectors.service.ILoginServiceInjector;
import com.app.group15.model.User;

public class LoginService implements ILoginService,ILoginServiceInjector{
    private UserAbstractDao userDao;

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

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		this.userDao=userDao;
		
	}

}
