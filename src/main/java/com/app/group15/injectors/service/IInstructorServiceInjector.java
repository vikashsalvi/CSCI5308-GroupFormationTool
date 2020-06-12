package com.app.group15.injectors.service;

import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.CourseStudentMapperAbstractDao;
import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserRoleAbstractDao;

public interface IInstructorServiceInjector {
	public void injectUserDao(UserAbstractDao userDao);
	public void injectUserRoleDao(UserRoleAbstractDao userRoleDao);
	public void injectCourseDao(CourseAbstractDao courseDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao );
}
