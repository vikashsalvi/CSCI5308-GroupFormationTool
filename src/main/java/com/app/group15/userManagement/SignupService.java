package com.app.group15.userManagement;

public class SignupService implements ISignupService,ISignUpServiceInjector{
	private UserAbstractDao userDao ;

	public boolean checkUserExists(String bannerId) {
		User user = userDao.getUserByBannerId(bannerId);
		boolean response;

		response = user.getBannerId() != null;
		return response;
	}

	public int createUser(User user, String role) {
		return userDao.saveUser(user, role);
	}

	@Override
	public void injectUserDao(UserAbstractDao userDao) {
		this.userDao=userDao;
		
	}
	
	
}
