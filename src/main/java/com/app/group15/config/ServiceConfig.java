package com.app.group15.config;

import com.app.group15.courseManagement.AssignTaServiceInjector;
import com.app.group15.courseManagement.CourseServiceInjector;
import com.app.group15.courseManagement.IAssignTAService;
import com.app.group15.courseManagement.ICourseService;
import com.app.group15.courseManagement.IInstructorService;
import com.app.group15.courseManagement.InstructorServiceInjector;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyService;
import com.app.group15.passwordPolicyManagement.PasswordPolicyServiceInjector;
import com.app.group15.userManagement.AuthorizationService;
import com.app.group15.userManagement.IAuthorizationService;
import com.app.group15.userManagement.ILoginService;
import com.app.group15.userManagement.ISessionService;
import com.app.group15.userManagement.ISignupService;
import com.app.group15.userManagement.IUserService;
import com.app.group15.userManagement.LoginServiceInjector;
import com.app.group15.userManagement.SessionService;
import com.app.group15.userManagement.SignUpServiceInjector;
import com.app.group15.userManagement.UserServiceInjector;

public class ServiceConfig {
	private static ServiceConfig singletonServiceConfig = null;
	
	private IAuthorizationService authorizationService;
	private IAssignTAService assignTaService;
    private ICourseService courseService;
    private IInstructorService instructorService;
    private ILoginService loginService;
    private ISessionService sessionService;
    private ISignupService signUpService;
    private IUserService userService;
    private IPasswordPolicyService passwordPolicyService;
    
    

	private ServiceConfig() {
    	assignTaService=new AssignTaServiceInjector().getAssignTaService();
        courseService=new CourseServiceInjector().getCourseService();
        instructorService=new InstructorServiceInjector().getInstructorService();
        loginService=new LoginServiceInjector().getLoginService();
        sessionService=new SessionService();
        signUpService=new SignUpServiceInjector().getSignUpService();
        userService=new UserServiceInjector().getUserService();
        authorizationService = new AuthorizationService();
    }
    
    public IAuthorizationService getAuthorizationService() {
		return authorizationService;
	}

	public static ServiceConfig getInstance() {
        if (null == ServiceConfig.getUniqueInstance()) {
        	singletonServiceConfig = new ServiceConfig();
        }
        return ServiceConfig.singletonServiceConfig;
    }
    
    private static ServiceConfig getUniqueInstance() {
        return singletonServiceConfig;
    }

	public IAssignTAService getAssignTaService() {
		return assignTaService;
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public IInstructorService getInstructorService() {
		return instructorService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public ISessionService getSessionService() {
		return sessionService;
	}

	public ISignupService getSignUpService() {
		return signUpService;
	}

	public IUserService getUserService() {
		return userService;
	}
    
	public IPasswordPolicyService getPasswordPolicy() {
		passwordPolicyService=new PasswordPolicyServiceInjector().getPasswordPolicyService();
		return passwordPolicyService;
	}
    
}
