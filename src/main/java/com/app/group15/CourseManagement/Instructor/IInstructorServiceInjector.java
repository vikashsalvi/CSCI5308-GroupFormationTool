package com.app.group15.CourseManagement.Instructor;

import com.app.group15.CourseManagement.CourseAbstractDao;
import com.app.group15.CourseManagement.Student.CourseStudentMapperAbstractDao;
import com.app.group15.UserManagement.UserAbstractDao;
import com.app.group15.UserManagement.UserRoleAbstractDao;

public interface IInstructorServiceInjector {
	public void injectUserDao(UserAbstractDao userDao);
	public void injectUserRoleDao(UserRoleAbstractDao userRoleDao);
	public void injectCourseDao(CourseAbstractDao courseDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao );
}
