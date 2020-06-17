package com.app.group15.courseManagement;

import java.util.List;

import com.app.group15.userManagement.User;

public interface ICourseService {

	public  List<Course> getCoursesList();
	public  Course getCourseDetails(int id);
	public User getCourseInstructor(int id);
	public List<User> getAllCourseInstructors(List<Course> courses);
	public void addOrUpdateInstructor(int courseId, int instructorId);
	public int addCourse(String courseName);
	public void deleteCourse(int courseId);
	public boolean isUserCourseAdmin(int courseId, int id);
	public List<Course> getStudentCourses(int id);
	public Course getStudentCourseAsTa(int id);
}
