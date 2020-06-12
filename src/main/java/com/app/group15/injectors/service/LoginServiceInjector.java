package com.app.group15.injectors.service;

import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.services.LoginService;

public class LoginServiceInjector {

	private LoginService loginService;
	
	public LoginServiceInjector() {
		loginService=new LoginService();
		loginService.injectUserDao(new UserDaoInjectorService().getUserDao());
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
}
