package com.app.group15.persistence.dao;

import java.util.ArrayList;

import com.app.group15.courseManagement.Course;

public class CourseDaoMock {

    public Course getCourseByCourseIdMock(String courseId) {
        Course courseEntity = new Course();

        if (courseId.equals("6")) {
            courseEntity.setName("CSCI5409");
            return courseEntity;
        } else {
            return courseEntity;
        }
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course c1 = new Course();
        c1.setName("CSCI5409");
        c1.setId(1);
        Course c2 = new Course();
        c2.setId(2);
        c2.setName("CSCI5410");
        courseList.add(c1);
        courseList.add(c2);
        return courseList;
    }

    public Course addCourse(String courseName) {
        Course c1 = new Course();
        c1.setName(courseName);
        c1.setId(10);
        return c1;
    }

    public Course deleteCourse(Course c) {
        if (c.getName() != "") {
            return null;
        }
        return c;
    }
}
