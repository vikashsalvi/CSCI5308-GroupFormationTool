package com.app.group15.Config;

import com.app.group15.CourseManagement.CourseAbstractDao;
import com.app.group15.CourseManagement.CourseDaoInjectorService;
import com.app.group15.CourseManagement.Instructor.CourseInstructorMapperAbstractDao;
import com.app.group15.CourseManagement.Instructor.CourseInstructorMapperDao;
import com.app.group15.CourseManagement.Instructor.CourseInstructorMapperDaoInjectorService;
import com.app.group15.CourseManagement.Student.CourseStudentMapperAbstractDao;
import com.app.group15.CourseManagement.Student.CourseStudentMapperDao;
import com.app.group15.CourseManagement.Student.CourseStudentMapperDaoInjectorService;
import com.app.group15.NotificationManagement.EmailNotifierImpl;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyAbstractDao;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyDao;
import com.app.group15.PasswordPolicyManagement.UserPasswordHistoryAbstractDao;
import com.app.group15.PasswordPolicyManagement.UserPasswordHistoryDao;
import com.app.group15.QuestionManager.QuestionManagerAbstractDao;
import com.app.group15.QuestionManager.QuestionManagerDao;
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
    private QuestionManagerAbstractDao questionManagerAbstractDao;


    private SurveyAbstractDao surveyDao;
    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;

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
        passwordPolicyDao = new PasswordPolicyDao();
        userPasswordHistoryDao = new UserPasswordHistoryDao();
        questionManagerAbstractDao = new QuestionManagerDao();

        surveyDao=new SurveyDaoInjectorService().getSurveyDao();
        surveyQuestionMapperDao=new SurveyQuestionMapperDao();

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

    public PasswordPolicyAbstractDao getPasswordPolicyDao() {
        return passwordPolicyDao;
    }

    public UserPasswordHistoryAbstractDao getUserPasswordHistoryDao() {
        return userPasswordHistoryDao;
    }

    public QuestionManagerAbstractDao getQuestionManagerAbstractDao() {
        return questionManagerAbstractDao;
    }

    public void setQuestionManagerAbstractDao(QuestionManagerAbstractDao questionManagerAbstractDao) {
        this.questionManagerAbstractDao = questionManagerAbstractDao;
    }
}
