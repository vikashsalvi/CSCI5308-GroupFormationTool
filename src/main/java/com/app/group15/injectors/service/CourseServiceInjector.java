package com.app.group15.injectors.service;

import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.CourseStudentMapperDao;
import com.app.group15.injectors.dao.CourseDaoInjectorService;
import com.app.group15.services.CourseService;

public class CourseServiceInjector {

	private CourseService courseService;
	
	public CourseServiceInjector() {
		courseService=new CourseService();
		courseService.injectCourseDao(new CourseDaoInjectorService().getCourseDao());
		courseService.injectCourseInstructorMapper(new CourseInstructorMapperDao());
		courseService.injectCourseStudentMapper(new CourseStudentMapperDao());
	}

	public CourseService getCourseService() {
		return courseService;
	}
	
}
