package com.app.group15.Config;

import com.app.group15.CourseManagement.CourseServiceInjector;
import com.app.group15.CourseManagement.ICourseService;
import com.app.group15.CourseManagement.Instructor.AssignTaServiceInjector;
import com.app.group15.CourseManagement.Instructor.IAssignTAService;
import com.app.group15.CourseManagement.Instructor.IInstructorService;
import com.app.group15.CourseManagement.Instructor.InstructorServiceInjector;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.PasswordPolicyManagement.IPasswordPolicyService;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyServiceInjector;
import com.app.group15.QuestionManager.IQuestionChoiceMapperService;
import com.app.group15.QuestionManager.IQuestionManagerService;
import com.app.group15.QuestionManager.QuestionChoiceMapperServiceInjector;
import com.app.group15.QuestionManager.QuestionManagerServiceInjector;
import com.app.group15.SurveyManagement.ISurveyService;
import com.app.group15.SurveyManagement.SurveyServiceInjector;
import com.app.group15.SurveyManagement.student.ISurveyStudentService;
import com.app.group15.SurveyManagement.student.SurveyStudentInjectorService;
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

import java.sql.SQLException;

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
   
    private IForgetPasswordService forgetPasswordService;
    private ISurveyService surveyService;
    private ISurveyStudentService surveyStudentService;

    private ServiceConfig() {
        assignTaService = new AssignTaServiceInjector().getAssignTaService();
        courseService = new CourseServiceInjector().getCourseService();
        instructorService = new InstructorServiceInjector().getInstructorService();
        loginService = new LoginServiceInjector().getLoginService();
        sessionService = new SessionService();
        signUpService = new SignUpServiceInjector().getSignUpService();
        userService = new UserServiceInjector().getUserService();
        authorizationService = new AuthorizationService();
        
        forgetPasswordService = new ForgetPasswordServiceInjector().getForgetPasswordService();
        surveyService = new SurveyServiceInjector().getSurveyService();
        surveyStudentService = new SurveyStudentInjectorService().getSurveyStudentService();
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

    public IAuthorizationService getAuthorizationService() {
        return authorizationService;
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

    public IPasswordPolicyService getPasswordPolicy() throws SQLException, AwsSecretsManagerException {
        passwordPolicyService = new PasswordPolicyServiceInjector().getPasswordPolicyService();
        return passwordPolicyService;
    }

    

    public IForgetPasswordService getForgetPasswordService() {
        return forgetPasswordService;
    }

    public ISurveyService getSurveyService() {
        return surveyService;
    }

    public ISurveyStudentService getSurveyStudentService() {
        return surveyStudentService;
    }

}
