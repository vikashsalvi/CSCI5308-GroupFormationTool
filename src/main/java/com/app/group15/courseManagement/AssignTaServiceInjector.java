package com.app.group15.courseManagement;


import com.app.group15.userManagement.UserDaoInjectorService;
import com.app.group15.userManagement.UserRoleDaoInjectorService;

public class AssignTaServiceInjector {
	private AssignTAService assignTaService;
	
	public AssignTaServiceInjector(){
		assignTaService=new AssignTAService();
		assignTaService.inectCourseDao(new CourseDaoInjectorService().getCourseDao());
		assignTaService.injectCourseInstructorMapper(new CourseInstructorMapperDao());
		assignTaService.injectInstructorService(new InstructorServiceInjector().getInstructorService());
		assignTaService.injectUserDao(new UserDaoInjectorService().getUserDao());
		assignTaService.injectUserRoleDao(new UserRoleDaoInjectorService().getUserRoleDao());
		
	}

	public AssignTAService getAssignTaService() {
		return assignTaService;
	}
	
	
}
