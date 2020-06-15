package com.app.group15.courseManagement;

public interface ICourseServiceInjector {

	public void injectCourseDao(CourseAbstractDao courseDao);
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao);
}
