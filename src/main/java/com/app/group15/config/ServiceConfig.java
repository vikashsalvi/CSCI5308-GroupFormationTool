package com.app.group15.config;

import com.app.group15.QuestionManager.IQuestionManagerService;
import com.app.group15.QuestionManager.QuestionManagerServiceInjector;
import com.app.group15.courseManagement.*;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyService;
import com.app.group15.passwordPolicyManagement.PasswordPolicyServiceInjector;
import com.app.group15.userManagement.*;

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
	private IQuestionManagerService questionManagerService;
    
    

	private ServiceConfig() {
		assignTaService = new AssignTaServiceInjector().getAssignTaService();
		courseService = new CourseServiceInjector().getCourseService();
		instructorService = new InstructorServiceInjector().getInstructorService();
		loginService = new LoginServiceInjector().getLoginService();
		sessionService = new SessionService();
		signUpService = new SignUpServiceInjector().getSignUpService();
		userService = new UserServiceInjector().getUserService();
		authorizationService = new AuthorizationService();
		questionManagerService = new QuestionManagerServiceInjector().getQuestionManagerService();
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
		passwordPolicyService = new PasswordPolicyServiceInjector().getPasswordPolicyService();
		return passwordPolicyService;
	}

	public IQuestionManagerService getQuestionManagerService() {
		return questionManagerService;
	}

}
