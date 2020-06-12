package com.app.group15.injectors.service;

import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.injectors.dao.UserRoleDaoInjectorService;
import com.app.group15.services.SignupService;

public class SignUpServiceInjector {
	
	private SignupService signUpService;
	
	public SignUpServiceInjector() {
		signUpService=new SignupService();
		signUpService.injectUserDao(new UserDaoInjectorService().getUserDao());
	}

	public SignupService getSignUpService() {
		return signUpService;
	}

	public void setSignUpService(SignupService signUpService) {
		this.signUpService = signUpService;
	}
	

}
