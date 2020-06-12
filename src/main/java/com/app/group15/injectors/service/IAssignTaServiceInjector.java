package com.app.group15.injectors.service;

import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserRoleAbstractDao;
import com.app.group15.services.IInstructorService;

public interface IAssignTaServiceInjector {
	
	public void injectUserDao(UserAbstractDao userDao);
	public void inectCourseDao(CourseAbstractDao courseDao);
	public void injectUserRoleDao(UserRoleAbstractDao userRoleDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectInstructorService(IInstructorService instructorService);

}
