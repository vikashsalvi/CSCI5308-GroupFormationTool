package com.app.group15.courseManagement;

public class CourseServiceInjector {

	private CourseService courseService;
	
	public CourseServiceInjector() {
		courseService=new CourseService();
		courseService.injectCourseDao(new CourseDaoInjectorService().getCourseDao());
		courseService.injectCourseInstructorMapper(new CourseInstructorMapperDao());
		courseService.injectCourseStudentMapper(new CourseStudentMapperDao());
	}

	public CourseService getCourseService() {
		return courseService;
	}
	
}
