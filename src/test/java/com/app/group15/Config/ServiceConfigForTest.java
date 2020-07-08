package com.app.group15.Config;

import com.app.group15.CourseManagement.CourseServiceInjectorForTest;
import com.app.group15.CourseManagement.ICourseService;
import com.app.group15.CourseManagement.Instructor.AssignTaServiceInjectorForTest;
import com.app.group15.CourseManagement.Instructor.IAssignTAService;
import com.app.group15.CourseManagement.Instructor.IInstructorService;
import com.app.group15.CourseManagement.Instructor.InstructorServiceInjectorForTest;

public class ServiceConfigForTest {
	private static ServiceConfigForTest singletonServiceConfig = null;
	
	private  ICourseService courseService;
	private IInstructorService instructorService;
	private IAssignTAService assignTaService;
	
	private ServiceConfigForTest() {
		courseService=new CourseServiceInjectorForTest().getCourseService();
		instructorService=new InstructorServiceInjectorForTest().getInstructorService();
		assignTaService=new AssignTaServiceInjectorForTest().getAssignTaService();
	}
	
	public static ServiceConfigForTest getInstance() {
        if (null == ServiceConfigForTest.getUniqueInstance()) {
            singletonServiceConfig = new ServiceConfigForTest();
        }
        return ServiceConfigForTest.singletonServiceConfig;
    }

    private static ServiceConfigForTest getUniqueInstance() {
        return singletonServiceConfig;
    }

	public ICourseService getCourseService() {
		return courseService;
	}

	public IInstructorService getInstructorService() {
		return instructorService;
	}

	public IAssignTAService getAssignTaService() {
		return assignTaService;
	}

	
	
	
	

    

}
