package com.app.group15.CourseManagement;

import com.app.group15.UserManagement.User;

import java.util.List;

public interface ICourseService {

    List<Course> getCoursesList();

    Course getCourseDetails(int id);

    User getCourseInstructor(int id);

    List<User> getAllCourseInstructors(List<Course> courses);

    void addOrUpdateInstructor(int courseId, int instructorId);

    int addCourse(String courseName);

    void deleteCourse(int courseId);

    boolean isUserCourseAdmin(int courseId, int id);

    List<Course> getStudentCourses(int id);

    Course getStudentCourseAsTa(int id);

    boolean validateCourseID(int courseId);

}
