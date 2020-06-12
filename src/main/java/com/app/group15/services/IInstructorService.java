package com.app.group15.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.group15.model.Course;
import com.app.group15.model.User;

public interface IInstructorService {
	public List<Course> getCourseOfInstructor(int instructorId);
	public User getCourseTA(int id);
	public List<User> getAllCourseTA(List<Course> courseEntities);
	public  boolean validateUserToAddAsTa(User user, int courseId);
	public void addOrUpdateStudentRole(User user, String role) ;
	public int addStudentsFromCSV(MultipartFile csvFile, int courseId);
	

}
