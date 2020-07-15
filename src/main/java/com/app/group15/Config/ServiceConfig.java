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
import com.app.group15.SurveyManagement.ISurveyService;
import com.app.group15.SurveyManagement.SurveyServiceInjector;
import com.app.group15.SurveyManagement.student.ISurveyStudentService;
import com.app.group15.SurveyManagement.student.SurveyStudentInjectorService;
import com.app.group15.UserManagement.SignupManagement.ISignupService;
import com.app.group15.UserManagement.SignupManagement.SignUpServiceInjector;
import java.sql.SQLException;

public class ServiceConfig {
    private static ServiceConfig singletonServiceConfig = null;

    private IAssignTAService assignTaService;
    private ICourseService courseService;
    private IInstructorService instructorService;
    private ISignupService signUpService;
    private IPasswordPolicyService passwordPolicyService;
    private ISurveyService surveyService;
    private ISurveyStudentService surveyStudentService;

    private ServiceConfig() {
        assignTaService = new AssignTaServiceInjector().getAssignTaService();
        courseService = new CourseServiceInjector().getCourseService();
        instructorService = new InstructorServiceInjector().getInstructorService();
        signUpService = new SignUpServiceInjector().getSignUpService();

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

    public IAssignTAService getAssignTaService() {
        return assignTaService;
    }

    public ICourseService getCourseService() {
        return courseService;
    }

    public IInstructorService getInstructorService() {
        return instructorService;
    }

    public ISignupService getSignUpService() {
        return signUpService;
    }

    public IPasswordPolicyService getPasswordPolicy() throws SQLException, AwsSecretsManagerException {
        passwordPolicyService = new PasswordPolicyServiceInjector().getPasswordPolicyService();
        return passwordPolicyService;
    }

    public ISurveyService getSurveyService() {
        return surveyService;
    }

    public ISurveyStudentService getSurveyStudentService() {
        return surveyStudentService;
    }

}
