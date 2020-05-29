package com.app.group15.persistence.injectors;

import com.app.group15.persistence.dao.CourseInstructorMapperDao;

public interface CourseInstructorMapperInjectorInterface {
		
	public void injectCourseInstructorMapperDao(CourseInstructorMapperDao courseInstructorMapperDao);
}
