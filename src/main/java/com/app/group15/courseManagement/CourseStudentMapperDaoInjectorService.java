package com.app.group15.courseManagement;

public class CourseStudentMapperDaoInjectorService {
	
	private CourseStudentMapperDao courseStudentMapperDao;
	
	public CourseStudentMapperDaoInjectorService() {
		courseStudentMapperDao = new CourseStudentMapperDao();
		
	}

	public CourseStudentMapperDao getCourseStudentMapperDao() {
		return courseStudentMapperDao;
	}

	

}
