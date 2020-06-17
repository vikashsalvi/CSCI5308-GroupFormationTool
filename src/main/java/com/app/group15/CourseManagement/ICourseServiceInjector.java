package com.app.group15.CourseManagement;

import com.app.group15.CourseManagement.Instructor.CourseInstructorMapperAbstractDao;
import com.app.group15.CourseManagement.Student.CourseStudentMapperAbstractDao;

public interface ICourseServiceInjector {

    public void injectCourseDao(CourseAbstractDao courseDao);

    public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao);

    public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao);
}
