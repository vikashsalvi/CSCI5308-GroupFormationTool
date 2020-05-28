package com.app.group15.persistence.injectors;

import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.dao.CourseInstructorMapperDao;


public class CourseInstructorMapperDaoInjectorService {

	private CourseInstructorMapperDao courseInstructorMapperDao;
	
	public CourseInstructorMapperDaoInjectorService() {
		courseInstructorMapperDao=new CourseInstructorMapperDao();
		courseInstructorMapperDao.injectConnection(DatabaseManager.getConnection());
		
	}

	public CourseInstructorMapperDao getCourseInstructorMapperDao() {
		return courseInstructorMapperDao;
	}

	
}
