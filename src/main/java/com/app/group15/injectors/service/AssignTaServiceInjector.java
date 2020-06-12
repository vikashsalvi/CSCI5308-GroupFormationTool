package com.app.group15.injectors.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.UserAbstractDao;
import com.app.group15.dao.UserDao;
import com.app.group15.dao.UserRoleAbstractDao;
import com.app.group15.injectors.dao.CourseDaoInjectorService;
import com.app.group15.injectors.dao.UserDaoInjectorService;
import com.app.group15.injectors.dao.UserRoleDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.services.AssignTAService;
import com.app.group15.services.IInstructorService;
import com.app.group15.services.InstructorService;

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
