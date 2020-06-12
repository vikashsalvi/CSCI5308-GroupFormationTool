package com.app.group15.injectors.service;

import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.CourseStudentMapperAbstractDao;
import com.app.group15.dao.CourseStudentMapperDao;

public interface ICourseServiceInjector {

	public void injectCourseDao(CourseAbstractDao courseDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao);
}
