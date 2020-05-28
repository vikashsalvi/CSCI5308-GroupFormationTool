package com.app.group15.persistence.injectors;

import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.dao.CourseDao;

public class CourseDaoInjectorService {
	
	private CourseDao courseDao;
	
	public CourseDaoInjectorService() {
		courseDao=new CourseDao();
		courseDao.injectConnection(DatabaseManager.getConnection());
		courseDao.injectCourseInstructorMapperDao(new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao());
		courseDao.injectCourseStudentMapperDao(new CourseStudentMapperDaoInjectorService().getCourseStudentMapperDao());
		
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	
	
	

}
