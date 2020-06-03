package com.app.group15.services;

import com.app.group15.dao.CourseDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.utility.GroupFormationToolLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class CourseService {
	private static CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
	private static CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();

	public static ArrayList<Course> getCoursesList() {
		return courseDao.getAll();
	}

	public static Course getCourseDetails(int id) {
		return courseDao.get(id);
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

	public static boolean isUserCourseAdmin(int courseId, int userId){
		User userTa=courseInstructorMapperDao.getCourseTA(courseId);
		User userInstructor=courseInstructorMapperDao.getCourseInstructor(courseId);
		GroupFormationToolLogger.log(Level.INFO, String.valueOf(courseId));
		GroupFormationToolLogger.log(Level.INFO, userTa.getBannerId()+" "+userInstructor.getBannerId());
		if (userId==userTa.getId() || userId==userInstructor.getId()){
			return true;
		}
		else{
			return false;
		}
	}
}
