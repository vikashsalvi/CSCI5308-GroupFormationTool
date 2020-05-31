//package com.app.group15;
//
//import java.sql.Connection;
//
//import com.app.group15.persistence.DatabaseManager;
//import com.app.group15.persistence.UserRole;
//
//import com.app.group15.persistence.dao.CourseDao;
//import com.app.group15.persistence.dao.CourseInstructorMapperDao;
//import com.app.group15.persistence.dao.CourseStudentMapperDao;
//import com.app.group15.persistence.dao.UserDao;
//import com.app.group15.persistence.dao.UserRoleDao;
//import com.app.group15.persistence.entity.CourseEntity;
//import com.app.group15.persistence.entity.UserEntity;
//import com.app.group15.persistence.injectors.CourseDaoInjectorService;
//import com.app.group15.persistence.injectors.CourseInstructorMapperDaoInjectorService;
//import com.app.group15.persistence.injectors.CourseStudentMapperDaoInjectorService;
//import com.app.group15.persistence.injectors.UserDaoInjectorService;
//import com.app.group15.persistence.injectors.UserRoleDaoInjectorService;
//
//public class Test {
//
//	public static void main(String[] args) {
//
//
//		// TODO Auto-generated method stub
//		UserRoleDao userRoleDao = new UserRoleDaoInjectorService().getUserRoleDao();
//		System.out.println(userRoleDao.getRolesByBannerId("B00843468"));
//		System.out.println(userRoleDao.get(2).getRole());
//		System.out.println(userRoleDao.getRoleId(UserRole.ADMIN.getRole()));
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
//		int userId=userDao.saveUser(user,UserRoleConstants.ROLE_GUEST);
//		System.out.println(userId);
//	}
//
//}
