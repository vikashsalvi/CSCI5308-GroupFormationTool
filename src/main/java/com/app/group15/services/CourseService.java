package com.app.group15.services;

import com.app.group15.dao.CourseDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.model.Course;

import java.util.ArrayList;

public class CourseService {
	public static ArrayList<Course> getCoursesList() {
		CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
		return courseDao.getAll();
	}
}
