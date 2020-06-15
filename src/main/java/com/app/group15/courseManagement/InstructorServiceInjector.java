package com.app.group15.courseManagement;

import com.app.group15.userManagement.UserDaoInjectorService;
import com.app.group15.userManagement.UserRoleDao;

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
