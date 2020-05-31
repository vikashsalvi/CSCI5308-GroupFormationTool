package com.app.group15.injectors;

import com.app.group15.dao.CourseStudentMapperDao;
import com.app.group15.persistence.DatabaseManager;

public class CourseStudentMapperDaoInjectorService {
	
	private CourseStudentMapperDao courseStudentMapperDao;
	
	public CourseStudentMapperDaoInjectorService() {
		courseStudentMapperDao = new CourseStudentMapperDao();
		courseStudentMapperDao.injectConnection(DatabaseManager.getConnection());
	}

	public CourseStudentMapperDao getCourseStudentMapperDao() {
		return courseStudentMapperDao;
	}

	

}
