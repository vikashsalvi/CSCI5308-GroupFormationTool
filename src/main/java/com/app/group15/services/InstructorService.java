package com.app.group15.services;

import com.app.group15.dao.CourseInstructorMapperDao;
import com.app.group15.injectors.CourseInstructorMapperDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;


import java.util.ArrayList;

public class InstructorService {

    private static CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();
    public ArrayList<Course> getCourseOfInstructor(int instructorId) {
        ArrayList<Course> arrayListCourses =courseInstructorMapperDao.getCourseByInstructor(instructorId);
        return  arrayListCourses;
    }

    public static User getCourseTA(int id) {
        User userEntity = courseInstructorMapperDao.getCourseTA(id);
        return userEntity;
    }

    public static ArrayList<User> getAllCourseTA(ArrayList<Course> courseEntities) {
        ArrayList<User> userEntitiesTa=new ArrayList<>();
        courseEntities.forEach(courseEntity -> userEntitiesTa.add(getCourseTA(courseEntity.getId())));
        return userEntitiesTa;
    }

}
