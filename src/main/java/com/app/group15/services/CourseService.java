package com.app.group15.services;

import com.app.group15.persistence.dao.CourseDao;
import com.app.group15.persistence.dao.CourseInstructorMapperDao;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.CourseDaoInjectorService;
import com.app.group15.persistence.injectors.CourseInstructorMapperDaoInjectorService;

import java.util.ArrayList;

public class CourseService {
	private static CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
	private static CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();

	public static ArrayList<CourseEntity> getCoursesList() {
		return courseDao.getAll();
	}

	public static CourseEntity getCourseDetails(int id) {
		return courseDao.get(id);
	}

	public static UserEntity getCourseInstructor(int id) {
		UserEntity userEntity = courseInstructorMapperDao.getCourseInstructor(id);
		return userEntity;
	}
	public static ArrayList<UserEntity> getAllCourseInstructors(ArrayList<CourseEntity> courseEntities) {
		ArrayList<UserEntity> userEntitiesInstructors=new ArrayList<>();
		courseEntities.forEach(courseEntity -> userEntitiesInstructors.add(getCourseInstructor(courseEntity.getId())));
		return userEntitiesInstructors;
	}
}
