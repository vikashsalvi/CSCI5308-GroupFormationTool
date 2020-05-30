package com.app.group15.config;


import com.app.group15.dao.CourseStudentMapperDao;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.injectors.CourseStudentMapperDaoInjectorService;
import com.app.group15.injectors.UserRoleDaoInjectorService;
import com.app.group15.notifications.EmailNotifierImpl;
import com.app.group15.notifications.Notifier;
import com.app.group15.persistence.DatabaseManager;
import com.app.group15.utility.GroupFormationToolLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class AppConfig {

    private static AppConfig singletonAppConfig = null;

    private EmailNotifierImpl emailNotifier;
    private GroupFormationToolLogger groupFormationToolLogger;
    private static Properties properties;
    private static String propertyFilePath = "src/main/resources/application.properties";
    private CourseStudentMapperDaoInjectorService courseStudentMapperDaoInjectorService;
    private CourseInstructorMapperDaoInjectorService courseInstructorMapperDaoInjectorService;
    private DatabaseManager databaseManager;
    private UserRoleDaoInjectorService userRoleDaoInjectorService;
    private CourseStudentMapperDao courseStudentMapperDao;


    private AppConfig(){
        emailNotifier = new EmailNotifierImpl();
        groupFormationToolLogger = new GroupFormationToolLogger();
        properties = new Properties();
        try (FileInputStream in = new FileInputStream(propertyFilePath)) {
            properties.load(in);
        } catch (IOException ex) {
            groupFormationToolLogger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        courseInstructorMapperDaoInjectorService = new CourseInstructorMapperDaoInjectorService();
        courseStudentMapperDaoInjectorService = new CourseStudentMapperDaoInjectorService();
        databaseManager = new DatabaseManager();
        userRoleDaoInjectorService = new UserRoleDaoInjectorService();
        courseStudentMapperDao = new CourseStudentMapperDao();

    }

    public static AppConfig getInstance() {
        return singletonAppConfig == null ?  singletonAppConfig = new AppConfig() : singletonAppConfig;
    }

    public EmailNotifierImpl getEmailNotifier() {
        return emailNotifier;
    }


    public void setEmailNotifier(EmailNotifierImpl emailNotifier){
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

    public void setCourseStudentMapperDaoInjectorService(CourseStudentMapperDaoInjectorService courseStudentMapperDaoInjectorService) {
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

    public void setCourseInstructorMapperDaoInjectorService(CourseInstructorMapperDaoInjectorService courseInstructorMapperDaoInjectorService) {
        this.courseInstructorMapperDaoInjectorService = courseInstructorMapperDaoInjectorService;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public UserRoleDaoInjectorService getUserRoleDaoInjectorService() {
        return userRoleDaoInjectorService;
    }

    public void setUserRoleDaoInjectorService(UserRoleDaoInjectorService userRoleDaoInjectorService) {
        this.userRoleDaoInjectorService = userRoleDaoInjectorService;
    }


    public CourseStudentMapperDao getCourseStudentMapperDao() {
        return courseStudentMapperDao;
    }

    public void setCourseStudentMapperDao(CourseStudentMapperDao courseStudentMapperDao) {
        this.courseStudentMapperDao = courseStudentMapperDao;
    }

}
