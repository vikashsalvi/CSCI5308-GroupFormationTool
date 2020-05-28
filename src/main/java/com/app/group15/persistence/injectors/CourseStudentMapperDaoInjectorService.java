package com.app.group15.persistence.injectors;

import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.dao.CourseStudentMapperDao;

public class CourseStudentMapperDaoInjectorService {
	
	private CourseStudentMapperDao courseStudentMapperDao;
	
	public CourseStudentMapperDaoInjectorService() {
		courseStudentMapperDao=new CourseStudentMapperDao();
		courseStudentMapperDao.injectConnection(DatabaseManager.getConnection());
		
	}

	public CourseStudentMapperDao getCourseStudentMapperDao() {
		return courseStudentMapperDao;
	}

	

}
