package com.app.group15.Config;

import com.app.group15.CourseManagement.CourseAbstractDao;
import com.app.group15.CourseManagement.CourseDaoInjectorService;
import com.app.group15.CourseManagement.CourseManagementConcreteFactory;
import com.app.group15.CourseManagement.ICourseManagementAbstractFactory;
import com.app.group15.CourseManagement.Instructor.*;
import com.app.group15.CourseManagement.Student.*;
import com.app.group15.GroupFormationManagement.GroupFormationAlgorithmConcreteFactory;
import com.app.group15.GroupFormationManagement.IGroupFormationAlgorithmAbstractFactory;
import com.app.group15.NotificationManagement.EmailNotifierImpl;
import com.app.group15.PasswordPolicyManagement.IPasswordPolicyAbstractFactory;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyConcreteFactory;
import com.app.group15.QuestionManager.IQuestionManagerAbstractFactory;
import com.app.group15.QuestionManager.QuestionManagerConcreteFactory;
import com.app.group15.SurveyManagement.ISurveyManagementAbstractFactory;
import com.app.group15.SurveyManagement.SurveyManagementConcreteFactory;
import com.app.group15.SurveyManagement.SurveyAbstractDao;
import com.app.group15.SurveyManagement.SurveyDaoInjectorService;
import com.app.group15.SurveyManagement.SurveyQuestionMapperAbstractDao;
import com.app.group15.SurveyManagement.SurveyQuestionMapperDao;
import com.app.group15.UserManagement.UserAbstractDao;
import com.app.group15.UserManagement.UserDao;
import com.app.group15.UserManagement.UserDaoInjectorService;
import com.app.group15.UserManagement.UserRoleDaoInjectorService;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class AppConfig {

    private static AppConfig singletonAppConfig = null;

    private EmailNotifierImpl emailNotifier;
    private GroupFormationToolLogger groupFormationToolLogger;
    private Properties properties;
    private CourseStudentMapperDaoInjectorService courseStudentMapperDaoInjectorService;
    private CourseInstructorMapperDaoInjectorService courseInstructorMapperDaoInjectorService;
    private UserRoleDaoInjectorService userRoleDaoInjectorService;
    private CourseStudentMapperAbstractDao courseStudentMapperDao;
    private CourseInstructorMapperAbstractDao courseInstructorMapperDao;
    private UserAbstractDao userDao;
    private CourseAbstractDao courseDao;
    private PasswordPolicyAbstractDao passwordPolicyDao;
    private UserPasswordHistoryAbstractDao userPasswordHistoryDao;

    private SurveyAbstractDao surveyDao;
    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;
    
    // Abstract Factory
    private IQuestionManagerAbstractFactory questionManagerAbstractFactory;
    private IGroupFormationAlgorithmAbstractFactory groupAlgorithmAbstractFactory;
    private ISurveyManagementAbstractFactory surveyManagementAbstractFactory;
    private IPasswordPolicyAbstractFactory passwordPolicyAbstractFactory;
    private ICourseManagementAbstractFactory courseManagementAbstractFactory;
    private ICourseInstructorAbstractFactory courseInstructorAbstractFactory;
    private ICourseStudentAbstractFactory courseStudentAbstractFactory;

    public SurveyAbstractDao getSurveyDao() {
        return surveyDao;
    }

    public SurveyQuestionMapperAbstractDao getSurveyQuestionMapperDao() {
        return surveyQuestionMapperDao;
    }

    private AppConfig() {

        properties = new Properties();
        String propertyFilePath = "src/main/resources/application.properties";
        try (FileInputStream in = new FileInputStream(propertyFilePath)) {
            properties.load(in);
        } catch (IOException ex) {
            GroupFormationToolLogger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        emailNotifier = new EmailNotifierImpl();
        groupFormationToolLogger = new GroupFormationToolLogger();

        courseInstructorMapperDaoInjectorService = new CourseInstructorMapperDaoInjectorService();
        courseStudentMapperDaoInjectorService = new CourseStudentMapperDaoInjectorService();
        userRoleDaoInjectorService = new UserRoleDaoInjectorService();
        courseStudentMapperDao = new CourseStudentMapperDao();
        courseInstructorMapperDao = new CourseInstructorMapperDao();
        userDao = new UserDaoInjectorService().getUserDao();

        courseDao = new CourseDaoInjectorService().getCourseDao();

        surveyDao = new SurveyDaoInjectorService().getSurveyDao();
        surveyQuestionMapperDao = new SurveyQuestionMapperDao();
        //ABSTRACT FACTORY
        questionManagerAbstractFactory = QuestionManagerConcreteFactory.getInstance();
        groupAlgorithmAbstractFactory = GroupFormationAlgorithmConcreteFactory.getInstance();
        courseManagementAbstractFactory = CourseManagementConcreteFactory.getInstance();
        courseInstructorAbstractFactory = CourseInstructorConcreteFactory.getInstance();
        courseStudentAbstractFactory = CourseStudentConcreteFactory.getInstance();
        surveyManagementAbstractFactory = SurveyManagementConcreteFactory.getInstance();
        passwordPolicyAbstractFactory = PasswordPolicyConcreteFactory.getInstance();
        passwordPolicyDao = new PasswordPolicyDao();
        userPasswordHistoryDao = new UserPasswordHistoryDao();
    }

    public static AppConfig getSingletonAppConfig() {
        return singletonAppConfig;
    }

    public static void setSingletonAppConfig(AppConfig singletonAppConfig) {
        AppConfig.singletonAppConfig = singletonAppConfig;
    }

    public static AppConfig getInstance() {
        if (null == AppConfig.getUniqueInstance()) {
            singletonAppConfig = new AppConfig();
        }
        return AppConfig.singletonAppConfig;
    }

    private static AppConfig getUniqueInstance() {
        return singletonAppConfig;
    }

    public CourseAbstractDao getCourseDao() {
        return courseDao;
    }

    public UserAbstractDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public CourseInstructorMapperAbstractDao getCourseInstructorMapperDao() {
        return courseInstructorMapperDao;
    }

    public void setCourseInstructorMapperDao(CourseInstructorMapperDao courseInstructorMapperDao) {
        this.courseInstructorMapperDao = courseInstructorMapperDao;
    }

    public EmailNotifierImpl getEmailNotifier() {
        return emailNotifier;
    }

    public void setEmailNotifier(EmailNotifierImpl emailNotifier) {
        this.emailNotifier = emailNotifier;
    }

    public GroupFormationToolLogger getGroupFormationToolLogger() {
        return groupFormationToolLogger;
    }

    public void setGroupFormationToolLogger(GroupFormationToolLogger groupFormationToolLogger) {
        this.groupFormationToolLogger = groupFormationToolLogger;
    }

    public CourseStudentMapperDaoInjectorService getCourseStudentMapperDaoInjectorService() {
        return courseStudentMapperDaoInjectorService;
    }

    public void setCourseStudentMapperDaoInjectorService(
            CourseStudentMapperDaoInjectorService courseStudentMapperDaoInjectorService) {
        this.courseStudentMapperDaoInjectorService = courseStudentMapperDaoInjectorService;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public CourseInstructorMapperDaoInjectorService getCourseInstructorMapperDaoInjectorService() {
        return courseInstructorMapperDaoInjectorService;
    }

    public void setCourseInstructorMapperDaoInjectorService(
            CourseInstructorMapperDaoInjectorService courseInstructorMapperDaoInjectorService) {
        this.courseInstructorMapperDaoInjectorService = courseInstructorMapperDaoInjectorService;
    }

    public UserRoleDaoInjectorService getUserRoleDaoInjectorService() {
        return userRoleDaoInjectorService;
    }

    public void setUserRoleDaoInjectorService(UserRoleDaoInjectorService userRoleDaoInjectorService) {
        this.userRoleDaoInjectorService = userRoleDaoInjectorService;
    }

    public CourseStudentMapperAbstractDao getCourseStudentMapperDao() {
        return courseStudentMapperDao;
    }

    public void setCourseStudentMapperDao(CourseStudentMapperDao courseStudentMapperDao) {
        this.courseStudentMapperDao = courseStudentMapperDao;
    }


    public IQuestionManagerAbstractFactory getQuestionManagerAbstractFactory() {
        return questionManagerAbstractFactory;
    }


    public ISurveyManagementAbstractFactory getSurveyManagementAbstractFactory() {
        return surveyManagementAbstractFactory;
    }

    public IPasswordPolicyAbstractFactory getPasswordPolicyAbstractFactory() {
        return passwordPolicyAbstractFactory;

    public IQuestionManagerAbstractFactory getQuestionManagerAbstractFactory() {
        return questionManagerAbstractFactory;
    }

    public IGroupFormationAlgorithmAbstractFactory getGroupAlgorithmAbstractFactory() {
        return groupAlgorithmAbstractFactory;
    }

    public ICourseManagementAbstractFactory getCourseManagementAbstractFactory() {
        return courseManagementAbstractFactory;
    }

    public void setCourseManagementAbstractFactory(ICourseManagementAbstractFactory courseManagementAbstractFactory) {
        this.courseManagementAbstractFactory = courseManagementAbstractFactory;
    }

    public ICourseInstructorAbstractFactory getCourseInstructorAbstractFactory() {
        return courseInstructorAbstractFactory;
    }

    public void setCourseInstructorAbstractFactory(ICourseInstructorAbstractFactory courseInstructorAbstractFactory) {
        this.courseInstructorAbstractFactory = courseInstructorAbstractFactory;
    }

    public ICourseStudentAbstractFactory getCourseStudentAbstractFactory() {
        return courseStudentAbstractFactory;
    }

    public void setCourseStudentAbstractFactory(ICourseStudentAbstractFactory courseStudentAbstractFactory) {
        this.courseStudentAbstractFactory = courseStudentAbstractFactory;
    }
}
