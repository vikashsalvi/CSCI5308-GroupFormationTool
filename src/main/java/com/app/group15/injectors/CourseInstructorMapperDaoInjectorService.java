package com.app.group15.injectors;

import com.app.group15.dao.CourseInstructorMapperDao;



public class CourseInstructorMapperDaoInjectorService {

	private CourseInstructorMapperDao courseInstructorMapperDao;
	
	public CourseInstructorMapperDaoInjectorService() {
		courseInstructorMapperDao=new CourseInstructorMapperDao();
	}

	public CourseInstructorMapperDao getCourseInstructorMapperDao() {
		return courseInstructorMapperDao;
	}


}
