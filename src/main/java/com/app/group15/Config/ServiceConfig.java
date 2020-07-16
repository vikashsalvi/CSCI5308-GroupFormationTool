package com.app.group15.Config;

import com.app.group15.CourseManagement.CourseServiceInjector;
import com.app.group15.CourseManagement.ICourseService;
import com.app.group15.CourseManagement.Instructor.AssignTaServiceInjector;
import com.app.group15.CourseManagement.Instructor.IAssignTAService;
import com.app.group15.CourseManagement.Instructor.IInstructorService;
import com.app.group15.CourseManagement.Instructor.InstructorServiceInjector;
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

    private ServiceConfig() {
        assignTaService = new AssignTaServiceInjector().getAssignTaService();
        courseService = new CourseServiceInjector().getCourseService();
        instructorService = new InstructorServiceInjector().getInstructorService();
        authorizationService = new AuthorizationService();
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
}
