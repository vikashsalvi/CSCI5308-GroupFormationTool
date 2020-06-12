package com.app.group15.injectors.dao;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.CourseDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.CourseStudentMapperDao;


public class CourseDaoInjectorService {

	private CourseDao courseDao;

	public CourseDaoInjectorService() {
		courseDao = new CourseDao();
		courseDao.injectCourseInstructorMapperDao(new CourseInstructorMapperDao());
		courseDao.injectCourseStudentMapperDao(new CourseStudentMapperDao());

	}

	public CourseDao getCourseDao() {
		return courseDao;
	}
}
