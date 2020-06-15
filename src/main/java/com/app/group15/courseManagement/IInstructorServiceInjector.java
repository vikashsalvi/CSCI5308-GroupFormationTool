package com.app.group15.courseManagement;

import com.app.group15.userManagement.UserAbstractDao;
import com.app.group15.userManagement.UserRoleAbstractDao;

public interface IInstructorServiceInjector {
	public void injectUserDao(UserAbstractDao userDao);
	public void injectUserRoleDao(UserRoleAbstractDao userRoleDao);
	public void injectCourseDao(CourseAbstractDao courseDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao );
}
