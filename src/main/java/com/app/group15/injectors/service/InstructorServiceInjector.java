package com.app.group15.injectors.service;

import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.CourseStudentMapperDao;
import com.app.group15.dao.UserRoleDao;
import com.app.group15.injectors.dao.CourseDaoInjectorService;
import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.services.InstructorService;

public class InstructorServiceInjector {

	private InstructorService instructorService;
	
	public InstructorServiceInjector() {
		instructorService=new InstructorService();
		instructorService.injectCourseDao(new CourseDaoInjectorService().getCourseDao());
		instructorService.injectCourseInstructorMapper(new CourseInstructorMapperDao());
		instructorService.injectCourseStudentMapper(new CourseStudentMapperDao());
		instructorService.injectUserDao(new UserDaoInjectorService().getUserDao());
		instructorService.injectUserRoleDao(new UserRoleDao());
		
	}

	public InstructorService getInstructorService() {
		return instructorService;
	}
	
	
}
