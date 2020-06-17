package com.app.group15.courseManagement;

import com.app.group15.userManagement.User;
import com.app.group15.utility.GroupFormationToolLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CourseService  implements ICourseService,ICourseServiceInjector{
	private CourseAbstractDao courseDao ;
	private CourseInstructorMapperAbstractDao courseInstructorMapperDao ;
	private CourseStudentMapperAbstractDao courseStudentMapperDao;

	public  List<Course> getCoursesList() {
		return courseDao.getAll();
	}

	public  Course getCourseDetails(int id) {
		return (Course) courseDao.get(id);
	}

	public User getCourseInstructor(int id) {
		User user = courseInstructorMapperDao.getCourseInstructor(id);
		return user;
	}

	public List<User> getAllCourseInstructors(List<Course> courses) {
		ArrayList<User> userInstructors = new ArrayList<>();
		courses.forEach(course -> userInstructors.add(getCourseInstructor(course.getId())));
		return userInstructors;
	}

	public  void addOrUpdateInstructor(int courseId, int instructorId) {
		courseInstructorMapperDao.addInstructorToACourse(courseId, instructorId);
	}

	public  int addCourse(String courseName) {
		Course course = new Course();
		course.setName(courseName);
		int courseId = courseDao.save(course);
		return courseId;
	}

	public  void deleteCourse(int courseId) {
		courseInstructorMapperDao.deleteByCourseId(courseId);
		courseDao.delete(courseId);
	}

	public  boolean isUserCourseAdmin(int courseId, int userId) {
		User userTa = courseInstructorMapperDao.getCourseTA(courseId);
		User userInstructor = courseInstructorMapperDao.getCourseInstructor(courseId);
		GroupFormationToolLogger.log(Level.INFO, String.valueOf(courseId));
		GroupFormationToolLogger.log(Level.INFO, userTa.getBannerId() + " " + userInstructor.getBannerId());
		if (userId == userTa.getId() || userId == userInstructor.getId()) {
			return true;
		} else {
			return false;
		}
	}

	public  List<Course> getStudentCourses(int studentId) {
		ArrayList<Integer> courseIdsOfAStudent = courseStudentMapperDao.getCourseIdsOfAStudent(studentId);
		ArrayList<Course> courses = new ArrayList<>();

		for (Integer courseId : courseIdsOfAStudent) {
			courses.add(getCourseDetails(courseId));
		}
		return courses;
	}

	public  Course getStudentCourseAsTa(int taId) {
		return courseInstructorMapperDao.getCourseByTa(taId);
	}

	@Override
	public void injectCourseDao(CourseAbstractDao courseDao) {
		this.courseDao=courseDao;
		
	}

	@Override
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao) {
		this.courseInstructorMapperDao=courseInstructorMapperDao;
	}

	@Override
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao) {
		this.courseStudentMapperDao=courseStudentMapperDao;
		
	}


	
}
