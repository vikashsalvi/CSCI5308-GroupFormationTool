package com.app.group15.services;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.CourseDao;
import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.dao.CourseStudentMapperDao;
import com.app.group15.dao.UserDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.injectors.CourseStudentMapperDaoInjectorService;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.utility.FileUtility;
import com.app.group15.utility.ForgetPasswordUtility;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class InstructorService {

	private static CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();
	private static CourseStudentMapperDao courseStudentMapperDao = new CourseStudentMapperDaoInjectorService().getCourseStudentMapperDao();
	private static UserDao userDao = new UserDaoInjectorService().getUserDao();
	private static CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();

	public ArrayList<Course> getCourseOfInstructor(int instructorId) {
		ArrayList<Course> arrayListCourses = courseInstructorMapperDao.getCourseByInstructor(instructorId);
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

	public static int addStudentsFromCSV(MultipartFile file, int courseId) {
		String[] cols;
		cols = new String[]{"name", "email", "banner_id"};
		ArrayList<HashMap<String, String>> data = FileUtility.readCSV(file, cols);
		String name, bannerId, email;
		Course course = courseDao.get(courseId);
		String emailSubject = new String("Update on your courses");
		String emailBody = new String(String.format("Welcome to the course %s.", course.getName()));
		int insertCount = 0;
		User user;
		if (data.size()>0 && data.get(0).get("error") != null) {
			return -1;
		}
		if (data.size() == 0) {
			return 0;
		} else {
			for (HashMap<String, String> dataRow : data) {
				name = dataRow.get("name");
				bannerId = dataRow.get("banner_id");
				email = dataRow.get("email");
				user = userDao.getUserByBannerId(bannerId);
				if (user.getBannerId() != null) {
					ArrayList<Integer> courseIdsOfAStudent = courseStudentMapperDao.getCourseIdsOfAStudent(user.getId());
					if (!courseIdsOfAStudent.contains(courseId)) {
						int courseStudentMapperId = courseStudentMapperDao.addStudentToACourse(courseId, user.getId());
						userDao.updateUserRole(user.getId(), "STUDENT");
						AppConfig.getInstance().getEmailNotifier().sendMessage(email, emailSubject, emailBody);
					} else {
						GroupFormationToolLogger.log(Level.INFO, String.format("%s is already registered to the course id %d", user.getFirstName(), courseId));
					}
				} else {
					String[] firstLast = name.split(" ");
					user.setFirstName(firstLast[0]);
					user.setLastName(firstLast[1]);
					user.setBannerId(bannerId);
					user.setEmail(email);
					String tempPassword = ForgetPasswordUtility.generateForgotPasswordToken();
					user.setPassword(tempPassword);
					userDao.saveUser(user, "STUDENT");
					int courseStudentMapperId = courseStudentMapperDao.addStudentToACourse(courseId, user.getId());
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
