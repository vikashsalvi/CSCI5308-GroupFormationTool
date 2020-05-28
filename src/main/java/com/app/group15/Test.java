package com.app.group15;

import java.sql.Connection;

import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.UserRoleConstants;
import com.app.group15.persistence.dao.CourseDao;
import com.app.group15.persistence.dao.CourseInstructorMapperDao;
import com.app.group15.persistence.dao.CourseStudentMapperDao;
import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.dao.UserRoleDao;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.CourseDaoInjectorService;
import com.app.group15.persistence.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.persistence.injectors.CourseStudentMapperDaoInjectorService;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import com.app.group15.persistence.injectors.UserRoleDaoInjectorService;

public class Test {

//	public static void main(String[] args) {
//
//		
//		
//		// TODO Auto-generated method stub
//		UserRoleDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();
//		System.out.println(userRoleDao.get(2).getRole());
//		System.out.println(userRoleDao.getRoleId(UserRoleConstants.ROLE_ADMIN));
//		UserDao userDao=new UserDaoInjectorService().getUserDao();
//		CourseDao courseDao=new CourseDaoInjectorService().getCourseDao();
//		CourseInstructorMapperDao courseInstructorMapperDao=new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();
//		CourseStudentMapperDao courseStudentMapperDao=new CourseStudentMapperDaoInjectorService().getCourseStudentMapperDao();
//		System.out.println(userDao.get(5).getFirstName());
//		UserEntity user=new UserEntity();
//		user.setBannerId("B10854477");
//		user.setEmail("sdagxvc@gmail.com");
//		user.setFirstName("rock2");
//		user.setLastName("mi");
//		user.setPassword("pass");
//		//int userId=userDao.saveUser(user,UserRoleConstants.ROLE_GUEST);
//		//System.out.println(userId);
//		userDao.update(user, 6);
//		userDao.updateUserRole(10, UserRoleConstants.ROLE_INSTRUCTOR);
//		userDao.updateUserPassword(9, "pass5");	
//		System.out.println(userRoleDao.get(1).getRole());
//		System.out.println(userRoleDao.getRoleId(UserRoleConstants.ROLE_ADMIN));
//		userRoleDao.updateRole(5, UserRoleConstants.ROLE_INSTRUCTOR);
//		System.out.println(userRoleDao.getAll().size());
//		System.out.println(courseDao.get(1).getName());
//		System.out.println(courseDao.getAll().size());
//		CourseEntity course=new CourseEntity();
//		course.setName("C5");
//		int courseId=courseDao.save(course);
//		System.out.println(courseId);
//		courseDao.update(course, 2);
//		userRoleDao.updateRole(6, UserRoleConstants.ROLE_STUDENT);
//		courseStudentMapperDao.addStudentToACourse(1, 6);
//		courseStudentMapperDao.deletByCourseId(1);
//		System.out.println(courseStudentMapperDao.getAll().size());
//		System.out.println(courseStudentMapperDao.getCourseIdsOfAStudent(6).size());
//		userRoleDao.updateRole(10, UserRoleConstants.ROLE_INSTRUCTOR);
//		courseInstructorMapperDao.addInstructorToACourse(2, 10);
//		userRoleDao.updateRole(9, UserRoleConstants.ROLE_TA);
//		courseInstructorMapperDao.addTaToACourse(1, 9);
//		courseDao.delete(1);
//	}

}
