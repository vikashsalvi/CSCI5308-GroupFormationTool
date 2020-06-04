package com.app.group15.persistence.dao;

import com.app.group15.model.Course;
import com.app.group15.model.CourseInstructorMapper;
import com.app.group15.model.User;

import java.util.ArrayList;

public class CourseInstructorMapperDaoMock {

    CourseInstructorMapper courseInstructorMapperEntity = new CourseInstructorMapper();

    public CourseInstructorMapper getCourseInstructorMapperEntity(int courseId)
    {
        if (courseId == 6) {
            courseInstructorMapperEntity.setCourseId(6);
            courseInstructorMapperEntity.setInstructorId(17);
            courseInstructorMapperEntity.setTaId(0);
        }

        return courseInstructorMapperEntity;
    }

    public boolean setTA(int courseId) {
        if (courseId == 6) {
            courseInstructorMapperEntity.setTaId(11);
            return true;
        } else {
            return false;
        }
    }

    public CourseInstructorMapper addOrUpdateInstructor(int id) {
        CourseInstructorMapper courseInstructorMapper = new CourseInstructorMapper();
        courseInstructorMapper.setInstructorId(id);
        courseInstructorMapper.setCourseId(1);
        return courseInstructorMapper;
    }

    public ArrayList<Course> getCoursesOfInstructorMock(int instructorId) {
        Course c1 = new Course();
        c1.setId(1);
        c1.setName("CSCI5410");
        Course c2 = new Course();
        c2.setId(2);
        c2.setName("CSCI5411");
        if (instructorId == 10) {
            ArrayList<Course> courses = new ArrayList<>();
            courses.add(c1);
            courses.add(c2);
            return courses;
        }
        return null;
    }

    public User getCourseTAMock(int courseId) {
        if (courseId == 10) {
            User user = new User();
            user.setBannerId("B00838074");
            user.setEmail("vikash.salvi@dal.ca");
            user.setFirstName("Vikash");
            user.setLastName("Salvi");
            user.setPassword("ABCD");
            user.setId(1);
            return user;
        }
        return null;
    }

    public ArrayList<User> getAllCourseTa(ArrayList<Course> courses) {

        ArrayList<User> userList = new ArrayList<>();
        courses.forEach(course -> {
            User u1 = new User();
            u1.setId(course.getId());
            userList.add(u1);
        });
        return userList;
    }

}
