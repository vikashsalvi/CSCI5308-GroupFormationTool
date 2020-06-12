package com.app.group15.injectors.dao;

import com.app.group15.dao.CourseStudentMapperDao;


public class CourseStudentMapperDaoInjectorService {
	
	private CourseStudentMapperDao courseStudentMapperDao;
	
	public CourseStudentMapperDaoInjectorService() {
		courseStudentMapperDao = new CourseStudentMapperDao();
		
	}

	public CourseStudentMapperDao getCourseStudentMapperDao() {
		return courseStudentMapperDao;
	}

	

}
