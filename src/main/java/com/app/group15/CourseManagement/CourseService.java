package com.app.group15.CourseManagement;

import com.app.group15.CourseManagement.Instructor.CourseInstructorMapperAbstractDao;
import com.app.group15.CourseManagement.Student.CourseStudentMapperAbstractDao;
import com.app.group15.UserManagement.User;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CourseService implements ICourseService, ICourseServiceInjector {
	private CourseAbstractDao courseDao;
	private CourseInstructorMapperAbstractDao courseInstructorMapperDao;
	private CourseStudentMapperAbstractDao courseStudentMapperDao;

	@Override
	public List<Course> getCoursesList() {
		return courseDao.getAll();
	}

	@Override
	public Course getCourseDetails(int id) {
		return (Course) courseDao.get(id);
	}

	@Override
	public User getCourseInstructor(int id) {
		User user = courseInstructorMapperDao.getCourseInstructor(id);
		return user;
	}

	@Override
	public List<User> getAllCourseInstructors(List<Course> courses) {
		if (ServiceUtility.isNotNull(courses)) {
			List<User> userInstructors = new ArrayList<>();
			courses.forEach(course -> userInstructors.add(getCourseInstructor(course.getId())));
			return userInstructors;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
		return null;
	}

	@Override
	public void addOrUpdateInstructor(int courseId, int instructorId) {
		if (ServiceUtility.isValidInt(courseId) && ServiceUtility.isValidInt(instructorId)) {
			courseInstructorMapperDao.addInstructorToACourse(courseId, instructorId);
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
	}

	@Override
	public int addCourse(String courseName) {
		if (ServiceUtility.isNotNull(courseName)) {
			Course course = new Course();
			course.setName(courseName);
			int courseId = courseDao.save(course);
			return courseId;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
		return -1;
	}

	@Override
	public void deleteCourse(int courseId) {
		if (ServiceUtility.isValidInt(courseId)) {
			courseInstructorMapperDao.deleteByCourseId(courseId);
			courseDao.delete(courseId);
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
	}

	@Override
	public boolean isUserCourseAdmin(int courseId, int userId) {

		if (ServiceUtility.isValidInt(courseId) && ServiceUtility.isValidInt(userId)) {
			User userTa = courseInstructorMapperDao.getCourseTA(courseId);
			User userInstructor = courseInstructorMapperDao.getCourseInstructor(courseId);
			GroupFormationToolLogger.log(Level.INFO, String.valueOf(courseId));
			GroupFormationToolLogger.log(Level.INFO, userTa.getBannerId() + " " + userInstructor.getBannerId());
			if (userId == userTa.getId() || userId == userInstructor.getId()) {
				return true;
			} else {
				return false;
			}
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
		return false;
	}

	@Override
	public List<Course> getStudentCourses(int studentId) {
		if (ServiceUtility.isValidInt(studentId)) {
			ArrayList<Integer> courseIdsOfAStudent = courseStudentMapperDao.getCourseIdsOfAStudent(studentId);
			ArrayList<Course> courses = new ArrayList<>();

			for (Integer courseId : courseIdsOfAStudent) {
				courses.add(getCourseDetails(courseId));
			}
			return courses;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
		return null;
	}

	@Override
	public Course getStudentCourseAsTa(int taId) {
		if (ServiceUtility.isValidInt(taId)) {
			return courseInstructorMapperDao.getCourseByTa(taId);
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
		return null;
	}

	@Override
	public void injectCourseDao(CourseAbstractDao courseDao) {
		if (ServiceUtility.isNotNull(courseDao)) {
			this.courseDao = courseDao;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "CourseDao injection issue in CourseService");
		}

	}

	@Override
	public void injectCourseInstructorMapper(CourseInstructorMapperAbstractDao courseInstructorMapperDao) {
		if (ServiceUtility.isNotNull(courseInstructorMapperDao)) {
			this.courseInstructorMapperDao = courseInstructorMapperDao;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "courseInstructorMapperDao injection issue in CourseService");
		}
	}

	@Override
	public void injectCourseStudentMapper(CourseStudentMapperAbstractDao courseStudentMapperDao) {

		if (ServiceUtility.isNotNull(courseStudentMapperDao)) {
			this.courseStudentMapperDao = courseStudentMapperDao;
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "courseStudentMapperDao injection issue in CourseService");
		}
	}

	@Override
	public boolean validateCourseID(int courseId) {
		if (ServiceUtility.isValidInt(courseId)) {
			if (courseDao.get(courseId) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
		}
		return false;
	}

}
