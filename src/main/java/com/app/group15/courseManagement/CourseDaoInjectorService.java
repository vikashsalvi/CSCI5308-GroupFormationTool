package com.app.group15.courseManagement;



public class CourseDaoInjectorService {

	private CourseDao courseDao;

	public CourseDaoInjectorService() {
		courseDao = new CourseDao();
		courseDao.injectCourseInstructorMapperDao(new CourseInstructorMapperDao());
		courseDao.injectCourseStudentMapperDao(new CourseStudentMapperDao());

	}

	public CourseDao getCourseDao() {
		return courseDao;
	}
}
