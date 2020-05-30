package com.app.group15.injectors;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.CourseStudentMapperDao;
import com.app.group15.persistence.DatabaseManager;

public class CourseStudentMapperDaoInjectorService {
	
	private CourseStudentMapperDao courseStudentMapperDao;
	
	public CourseStudentMapperDaoInjectorService() {
		courseStudentMapperDao=AppConfig.getInstance().getCourseStudentMapperDao();
		courseStudentMapperDao.injectConnection(AppConfig.getInstance().getDatabaseManager().getConnection());
		
	}

	public CourseStudentMapperDao getCourseStudentMapperDao() {
		return courseStudentMapperDao;
	}

	

}
