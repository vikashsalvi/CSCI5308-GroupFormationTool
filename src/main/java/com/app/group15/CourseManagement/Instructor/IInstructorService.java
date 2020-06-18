package com.app.group15.CourseManagement.Instructor;

import com.app.group15.CourseManagement.Course;
import com.app.group15.UserManagement.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IInstructorService {
    List<Course> getCourseOfInstructor(int instructorId);

    User getCourseTA(int id);

    List<User> getAllCourseTA(List<Course> courseEntities);

    boolean validateUserToAddAsTa(User user, int courseId);

    void addOrUpdateStudentRole(User user, String role);

    int addStudentsFromCSV(MultipartFile csvFile, int courseId);

    boolean checkInstructorPermission(int instructorId, int courseId);

}
