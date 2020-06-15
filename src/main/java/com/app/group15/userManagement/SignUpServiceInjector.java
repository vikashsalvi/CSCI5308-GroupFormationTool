package com.app.group15.userManagement;

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
