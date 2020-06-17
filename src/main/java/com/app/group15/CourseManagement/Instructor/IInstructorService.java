package com.app.group15.CourseManagement.Instructor;

import com.app.group15.CourseManagement.Course;
import com.app.group15.UserManagement.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IInstructorService {
	public List<Course> getCourseOfInstructor(int instructorId);

	public User getCourseTA(int id);

	public List<User> getAllCourseTA(List<Course> courseEntities);

	public boolean validateUserToAddAsTa(User user, int courseId);

	public void addOrUpdateStudentRole(User user, String role);

	public int addStudentsFromCSV(MultipartFile csvFile, int courseId);

	public boolean checkInstructorPermission(int instructorId, int courseId);

}
