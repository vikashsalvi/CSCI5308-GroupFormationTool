package com.app.group15.injectors;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.CourseDao;


public class CourseDaoInjectorService {

	private CourseDao courseDao;

	public CourseDaoInjectorService() {
		courseDao = new CourseDao();
		courseDao.injectCourseInstructorMapperDao(AppConfig.getInstance().getCourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao());
		courseDao.injectCourseStudentMapperDao(AppConfig.getInstance().getCourseStudentMapperDaoInjectorService().getCourseStudentMapperDao());

	}

	public CourseDao getCourseDao() {
		return courseDao;
	}
}
