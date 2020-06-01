package com.app.group15.services;

import com.app.group15.persistence.dao.CourseInstructorMapperDao;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.CourseInstructorMapperEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.CourseInstructorMapperDaoInjectorService;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class InstructorService {

    private static CourseInstructorMapperDao courseInstructorMapperDao = new CourseInstructorMapperDaoInjectorService().getCourseInstructorMapperDao();
    public ArrayList<CourseEntity> getCourseOfInstructor(int instructorId) {
        ArrayList<CourseEntity> arrayListCourses =courseInstructorMapperDao.getCourseByInstructor(instructorId);
        return  arrayListCourses;
    }

    public static UserEntity getCourseTA(int id) {
        UserEntity userEntity = courseInstructorMapperDao.getCourseTA(id);
        return userEntity;
    }

    public static ArrayList<UserEntity> getAllCourseTA(ArrayList<CourseEntity> courseEntities) {
        ArrayList<UserEntity> userEntitiesTa=new ArrayList<>();
        courseEntities.forEach(courseEntity -> userEntitiesTa.add(getCourseTA(courseEntity.getId())));
        return userEntitiesTa;
    }

}
