package com.app.group15.config;

import com.app.group15.CourseManagement.CourseServiceInjector;
import com.app.group15.CourseManagement.ICourseService;
import com.app.group15.CourseManagement.Instructor.AssignTaServiceInjector;
import com.app.group15.CourseManagement.Instructor.IAssignTAService;
import com.app.group15.CourseManagement.Instructor.IInstructorService;
import com.app.group15.CourseManagement.Instructor.InstructorServiceInjector;
import com.app.group15.PasswordPolicyManagement.IPasswordPolicyService;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyServiceInjector;
import com.app.group15.QuestionManager.IQuestionChoiceMapperService;
import com.app.group15.QuestionManager.IQuestionManagerService;
import com.app.group15.QuestionManager.QuestionChoiceMapperServiceInjector;
import com.app.group15.QuestionManager.QuestionManagerServiceInjector;
import com.app.group15.UserManagement.ForgetPassword.ForgetPasswordServiceInjector;
import com.app.group15.UserManagement.ForgetPassword.IForgetPasswordService;
import com.app.group15.UserManagement.IUserService;
import com.app.group15.UserManagement.LoginManagement.ILoginService;
import com.app.group15.UserManagement.LoginManagement.LoginServiceInjector;
import com.app.group15.UserManagement.SessionManagement.AuthorizationService;
import com.app.group15.UserManagement.SessionManagement.IAuthorizationService;
import com.app.group15.UserManagement.SessionManagement.ISessionService;
import com.app.group15.UserManagement.SessionManagement.SessionService;
import com.app.group15.UserManagement.SignupManagement.ISignupService;
import com.app.group15.UserManagement.SignupManagement.SignUpServiceInjector;
import com.app.group15.UserManagement.UserServiceInjector;

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
	private IQuestionChoiceMapperService questionChoiceMapperService;
	private IForgetPasswordService forgetPasswordService;

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
		questionChoiceMapperService = new QuestionChoiceMapperServiceInjector().getQuestionChoiceMapperService();
		forgetPasswordService = new ForgetPasswordServiceInjector().getForgetPasswordService();
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

	public IQuestionChoiceMapperService getQuestionChoiceMapperService() {
		return questionChoiceMapperService;
	}


	public IForgetPasswordService getForgetPasswordService() {
		return forgetPasswordService;
	}

}
