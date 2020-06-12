package com.app.group15.config;

import com.app.group15.injectors.service.AssignTaServiceInjector;
import com.app.group15.injectors.service.CourseServiceInjector;
import com.app.group15.injectors.service.InstructorServiceInjector;
import com.app.group15.injectors.service.LoginServiceInjector;
import com.app.group15.injectors.service.SignUpServiceInjector;
import com.app.group15.injectors.service.UserServiceInjector;
import com.app.group15.services.AssignTAService;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.IAssignTAService;
import com.app.group15.services.IAuthorizationService;
import com.app.group15.services.ICourseService;
import com.app.group15.services.IInstructorService;
import com.app.group15.services.ILoginService;
import com.app.group15.services.ISessionService;
import com.app.group15.services.ISignupService;
import com.app.group15.services.IUserService;
import com.app.group15.services.InstructorService;
import com.app.group15.services.LoginService;
import com.app.group15.services.SessionService;
import com.app.group15.services.SignupService;
import com.app.group15.services.UserService;

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
    
    
}
