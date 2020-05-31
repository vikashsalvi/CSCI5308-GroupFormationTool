package com.app.group15.injectors;

import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.persistence.DatabaseManager;


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
