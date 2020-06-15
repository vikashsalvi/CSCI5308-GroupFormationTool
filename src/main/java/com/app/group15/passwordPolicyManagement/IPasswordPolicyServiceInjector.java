package com.app.group15.passwordPolicyManagement;

import java.util.List;

public interface IPasswordPolicyServiceInjector {
	public void injectPasswordPolicy(List<IPasswordPolicyValidator> passwordPolicyList) ;

}
