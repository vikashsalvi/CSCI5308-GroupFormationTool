package com.app.group15.injectors;


import com.app.group15.dao.CourseInstructorMapperDao;

public interface CourseInstructorMapperInjectorInterface {

	public void injectCourseInstructorMapperDao(CourseInstructorMapperDao courseInstructorMapperDao);
}
