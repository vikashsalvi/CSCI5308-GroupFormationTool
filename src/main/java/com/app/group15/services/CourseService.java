package com.app.group15.services;

import com.app.group15.persistence.dao.CourseDao;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.injectors.CourseDaoInjectorService;

import java.util.ArrayList;

public class CourseService {
	public static ArrayList<CourseEntity> getCoursesList(){
		CourseDao courseDao=new CourseDaoInjectorService().getCourseDao();
		return courseDao.getAll();
	}
}
