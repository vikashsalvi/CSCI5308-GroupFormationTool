package com.app.group15.injectors.service;

import com.app.group15.passwordPolicy.PasswordPolicyCharNotAllowed;
import com.app.group15.passwordPolicy.PasswordPolicyHistoryConstraint;
import com.app.group15.services.PasswordPolicyService;

public class PasswordPolicyServiceInjector {
	
	private PasswordPolicyService passwordPolicyService;
	
	public PasswordPolicyServiceInjector () {
		passwordPolicyService=new PasswordPolicyService();
		int x=5;
		if(x==3) {
			passwordPolicyService.injectPasswordPolicy(new PasswordPolicyCharNotAllowed());
			
		}else if(x==5) {
			passwordPolicyService.injectPasswordPolicy(new PasswordPolicyHistoryConstraint());
		}
	}

}
