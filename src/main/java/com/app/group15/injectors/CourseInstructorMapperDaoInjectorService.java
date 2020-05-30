package com.app.group15.injectors;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.persistence.DatabaseManager;


public class CourseInstructorMapperDaoInjectorService {

	private CourseInstructorMapperDao courseInstructorMapperDao;
	
	public CourseInstructorMapperDaoInjectorService() {
		courseInstructorMapperDao=new CourseInstructorMapperDao();
		courseInstructorMapperDao.injectConnection(AppConfig.getInstance().getDatabaseManager().getConnection());
		
	}

	public CourseInstructorMapperDao getCourseInstructorMapperDao() {
		return courseInstructorMapperDao;
	}

	
}
