package com.app.group15.services;

import com.app.group15.dao.CourseAbstractDao;
import com.app.group15.dao.CourseDao;
import com.app.group15.dao.CourseInstructorMapperAbstractDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;

import java.util.ArrayList;

public class CourseService {
	private static CourseAbstractDao courseDao = new CourseDaoInjectorService().getCourseDao();
	private static CourseInstructorMapperAbstractDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();

	public static ArrayList<Course> getCoursesList() {
		return courseDao.getAll();
	}

	public static Course getCourseDetails(int id) {
		return (Course) courseDao.get(id);
	}

	public static User getCourseInstructor(int id) {
		User user = courseInstructorMapperDao.getCourseInstructor(id);
		return user;
	}

	public static ArrayList<User> getAllCourseInstructors(ArrayList<Course> courses) {
		ArrayList<User> userInstructors = new ArrayList<>();
		courses.forEach(course -> userInstructors.add(getCourseInstructor(course.getId())));
		return userInstructors;
	}

	public static void addOrUpdateInstructor(int courseId, int instructorId) {
		courseInstructorMapperDao.addInstructorToACourse(courseId, instructorId);
	}

	public static int addCourse(String courseName) {
		Course course = new Course();
		course.setName(courseName);
		int courseId = courseDao.save(course);
		return courseId;
	}
	public static void deleteCourse(int courseId){
		courseInstructorMapperDao.deleteByCourseId(courseId);
		courseDao.delete(courseId);
	}
}
