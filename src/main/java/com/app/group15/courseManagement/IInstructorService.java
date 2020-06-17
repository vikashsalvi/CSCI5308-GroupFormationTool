package com.app.group15.courseManagement;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.group15.userManagement.User;

public interface IInstructorService {
	public List<Course> getCourseOfInstructor(int instructorId);
	public User getCourseTA(int id);
	public List<User> getAllCourseTA(List<Course> courseEntities);
	public  boolean validateUserToAddAsTa(User user, int courseId);
	public void addOrUpdateStudentRole(User user, String role) ;
	public int addStudentsFromCSV(MultipartFile csvFile, int courseId);
	public boolean checkIntructorPermission(int instructorId, int courseId);
	

}
