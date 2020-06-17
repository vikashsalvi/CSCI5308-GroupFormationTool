package com.app.group15.courseManagement;

import com.app.group15.userManagement.UserAbstractDao;
import com.app.group15.userManagement.UserRoleAbstractDao;

public interface IAssignTaServiceInjector {
	
	public void injectUserDao(UserAbstractDao userDao);
	public void inectCourseDao(CourseAbstractDao courseDao);
	public void injectUserRoleDao(UserRoleAbstractDao userRoleDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectInstructorService(IInstructorService instructorService);

}
