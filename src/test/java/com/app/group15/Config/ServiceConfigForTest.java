package com.app.group15.Config;

import com.app.group15.CourseManagement.CourseServiceInjectorForTest;
import com.app.group15.CourseManagement.ICourseService;
import com.app.group15.CourseManagement.Instructor.AssignTaServiceInjectorForTest;
import com.app.group15.CourseManagement.Instructor.IAssignTAService;
import com.app.group15.CourseManagement.Instructor.IInstructorService;
import com.app.group15.CourseManagement.Instructor.InstructorServiceInjectorForTest;
import com.app.group15.QuestionManager.IQuestionChoiceMapperService;
import com.app.group15.QuestionManager.IQuestionManagerService;
import com.app.group15.QuestionManager.QuestionChoiceMapperServiceInjectorForTest;
import com.app.group15.QuestionManager.QuestionManagerServiceInjectorForTest;
import com.app.group15.SurveyManagement.ISurveyService;
import com.app.group15.SurveyManagement.SurveyServiceInjectorForTest;
import com.app.group15.UserManagement.IUserService;
import com.app.group15.UserManagement.LoginManagement.ILoginService;
import com.app.group15.UserManagement.LoginManagement.LoginServiceInjectorForTest;
import com.app.group15.UserManagement.SignupManagement.ISignupService;
import com.app.group15.UserManagement.SignupManagement.SignUpServiceInjectorForTest;
import com.app.group15.UserManagement.UserServiceInjectorForTest;

public class ServiceConfigForTest {
	private static ServiceConfigForTest singletonServiceConfig = null;

	private ICourseService courseService;
	private IInstructorService instructorService;
	private IAssignTAService assignTaService;
	private IQuestionManagerService questionManagerService;
	private IQuestionChoiceMapperService questionChoiceMapperService;
	private IUserService userService;
	private ILoginService loginService;
	private ISignupService signupService;
	private ISurveyService surveyService;

	private ServiceConfigForTest() {
		courseService = new CourseServiceInjectorForTest().getCourseService();
		instructorService = new InstructorServiceInjectorForTest().getInstructorService();
		assignTaService = new AssignTaServiceInjectorForTest().getAssignTaService();
		questionManagerService = new QuestionManagerServiceInjectorForTest().getQuestionManagerService();
		questionChoiceMapperService = new QuestionChoiceMapperServiceInjectorForTest().getQuestionChoiceMapperService();
		userService = new UserServiceInjectorForTest().getUserService();
		loginService = new LoginServiceInjectorForTest().getLoginService();
		signupService = new SignUpServiceInjectorForTest().getSignUpService();
		surveyService=new SurveyServiceInjectorForTest().getSurveyService();
	}

	public static ServiceConfigForTest getInstance() {
        if (null == ServiceConfigForTest.getUniqueInstance()) {
            singletonServiceConfig = new ServiceConfigForTest();
        }
        return ServiceConfigForTest.singletonServiceConfig;
    }

    private static ServiceConfigForTest getUniqueInstance() {
        return singletonServiceConfig;
    }

	public ICourseService getCourseService() {
		return courseService;
	}

	public IInstructorService getInstructorService() {
		return instructorService;
	}

	public IAssignTAService getAssignTaService() {
		return assignTaService;
	}

	public IQuestionManagerService getQuestionManagerService() {
		return questionManagerService;
	}

	public IQuestionChoiceMapperService getQuestionChoiceMapperService() {
		return questionChoiceMapperService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public ISignupService getSignupService() {
		return signupService;
	}

	public ISurveyService getSurveyService() {
		return surveyService;
	}
}
