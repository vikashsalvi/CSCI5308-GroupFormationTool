package com.app.group15.services;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.*;
import com.app.group15.injectors.*;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.utility.FileUtility;
import com.app.group15.utility.ForgetPasswordUtility;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public class InstructorService {

	private static CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();
	private static CourseStudentMapperDao courseStudentMapperDao = new CourseStudentMapperDaoInjectorService().getCourseStudentMapperDao();
	private static UserDao userDao = new UserDaoInjectorService().getUserDao();
	private static UserRoleDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();
	private static CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();

	public ArrayList<Course> getCourseOfInstructor(int instructorId) {
		ArrayList<Course> arrayListCourses = courseInstructorMapperDao.getCoursesByInstructor(instructorId);
		return arrayListCourses;
	}

	public static User getCourseTA(int id) {
		User userEntity = courseInstructorMapperDao.getCourseTA(id);
		return userEntity;
	}

	public static ArrayList<User> getAllCourseTA(ArrayList<Course> courseEntities) {
		ArrayList<User> userEntitiesTa = new ArrayList<>();
		courseEntities.forEach(courseEntity -> userEntitiesTa.add(getCourseTA(courseEntity.getId())));
		return userEntitiesTa;
	}

	public static boolean validateUserToAddAsStudent(User user) {
		Set<String> userRoles = userRoleDao.getRolesByBannerId(user.getBannerId());
		boolean valid;
		if (userRoles.contains("INSTRUCTOR")) {
			GroupFormationToolLogger.log(Level.INFO, String.format("User with banner id %s is INSTRUCTOR", user.getBannerId()));
			valid = false;
		} else if (userRoles.contains("TA")) {
			Course course = courseInstructorMapperDao.getCourseByTa(user.getId());
			if (course.getName() != null) {
				GroupFormationToolLogger.log(Level.INFO, String.format("User with banner id %s is TA for this course (course id %d)", user.getBannerId(), course.getId()));
				valid = false;
			} else {
				valid = true;
			}
		} else {
			valid = true;
		}
		return valid;
	}

	public static boolean validateUserToAddAsTa(User user, int courseId) {
		boolean valid;
		ArrayList<Integer> courseIdsOfAStudent = courseStudentMapperDao.getCourseIdsOfAStudent(user.getId());
		valid = !courseIdsOfAStudent.contains(courseId);
		return valid;
	}

	public static void addOrUpdateStudentRole(User user, String role) {
		Set<String> userRoles = userRoleDao.getRolesByBannerId(user.getBannerId());
		if (!userRoles.contains(role)) {
			userRoleDao.addRole(user.getId(), role);
		} else {
			GroupFormationToolLogger.log(Level.INFO, String.format("User with banner id %s is already a student", user.getBannerId()));
		}
	}

	public static int addStudentsFromCSV(MultipartFile file, int courseId) {
		String[] cols;
		cols = new String[]{"name", "email", "banner_id"};
		ArrayList<HashMap<String, String>> data = FileUtility.readCSV(file, cols);
		String name, bannerId, email;
		Course course = courseDao.get(courseId);
		String emailSubject = new String("Update on your courses");
		int insertCount = 0;
		User user;
		if (data.size() > 0 && data.get(0).get("error") != null) {
			return -1;
		}
		if (data.size() == 0) {
			return 0;
		} else {
			for (HashMap<String, String> dataRow : data) {
				String emailBody = String.format("Welcome to the course %s.", course.getName());
				name = dataRow.get("name");
				bannerId = dataRow.get("banner_id");
				email = dataRow.get("email");
				user = userDao.getUserByBannerId(bannerId);
				GroupFormationToolLogger.log(Level.INFO, "Getting user by banner id");
				if (user.getBannerId() != null) {
//							GroupFormationToolLogger.log(Level.INFO, String.format("%s already exists!", user.getBannerId()));
					if (validateUserToAddAsStudent(user)) {
						ArrayList<Integer> courseIdsOfAStudent = courseStudentMapperDao.getCourseIdsOfAStudent(user.getId());
//							GroupFormationToolLogger.log(Level.INFO, "Checking if student is already enrolled");
						if (!courseIdsOfAStudent.contains(courseId)) {
//							GroupFormationToolLogger.log(Level.INFO, String.format("%s not enrolled!", user.getBannerId()));
							int courseStudentMapperId = courseStudentMapperDao.addStudentToACourse(courseId, user.getId());
//							GroupFormationToolLogger.log(Level.INFO, String.format("%d is CourseStudentMapperId for %s!", courseStudentMapperId, user.getBannerId()));
							addOrUpdateStudentRole(user, "STUDENT");
							AppConfig.getInstance().getEmailNotifier().sendMessage(email, emailSubject, emailBody);
						} else {
							GroupFormationToolLogger.log(Level.INFO, String.format("%s is already registered to the course id %d", user.getFirstName(), courseId));
						}
					} else {
						GroupFormationToolLogger.log(Level.INFO, String.format("%s is an Instructor", user.getFirstName()));
					}
				} else {
//					GroupFormationToolLogger.log(Level.INFO, String.format("%s not registered in system!", user.getBannerId()));
					String[] firstLast = name.split(" ");
					user.setFirstName(firstLast[0]);
					user.setLastName(firstLast[1]);
					user.setBannerId(bannerId);
					user.setEmail(email);
					String tempPassword = ForgetPasswordUtility.generateForgotPasswordToken();
					user.setPassword(tempPassword);
					int userId = userDao.saveUser(user, "STUDENT");
					user.setId(userId);
//					GroupFormationToolLogger.log(Level.INFO, String.format("%s registered with id %d!", user.getBannerId(), userId));
					int courseStudentMapperId = courseStudentMapperDao.addStudentToACourse(courseId, userId);
//					GroupFormationToolLogger.log(Level.INFO, String.format("%d is CourseStudentMapperId for %s!", courseStudentMapperId, user.getBannerId()));
					addOrUpdateStudentRole(user, "STUDENT");
					userDao.updateUserRole(user.getId(), "STUDENT");
					emailBody += String.format("Your temporary password is %s.\nPlease change the password once by clicking on Forgot password on login page", tempPassword);
					AppConfig.getInstance().getEmailNotifier().sendMessage(email, emailSubject, emailBody);
				}
				insertCount++;
			}
		}
		return insertCount;
	}
}
