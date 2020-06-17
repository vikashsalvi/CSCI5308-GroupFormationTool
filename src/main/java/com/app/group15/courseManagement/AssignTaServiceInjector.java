package com.app.group15.courseManagement;


import com.app.group15.userManagement.UserDaoInjectorService;
import com.app.group15.userManagement.UserRoleDaoInjectorService;
import com.app.group15.userManagement.UserServiceInjector;

public class AssignTaServiceInjector {
	private AssignTAService assignTaService;
	
	public AssignTaServiceInjector(){
		assignTaService=new AssignTAService();
		assignTaService.inectCourseDao(new CourseDaoInjectorService().getCourseDao());
		assignTaService.injectCourseInstructorMapper(new CourseInstructorMapperDao());
		assignTaService.injectInstructorService(new InstructorServiceInjector().getInstructorService());
		assignTaService.injectUserDao(new UserDaoInjectorService().getUserDao());
		assignTaService.injectUserRoleDao(new UserRoleDaoInjectorService().getUserRoleDao());
		assignTaService.injectCourseService(new CourseServiceInjector().getCourseService());
		assignTaService.injectUserService(new UserServiceInjector().getUserService());
		
	}

	public AssignTAService getAssignTaService() {
		return assignTaService;
	}
	
	
}
