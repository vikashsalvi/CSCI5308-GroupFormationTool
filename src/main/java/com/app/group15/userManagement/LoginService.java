package com.app.group15.userManagement;

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
