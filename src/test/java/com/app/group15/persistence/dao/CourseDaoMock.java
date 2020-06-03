package com.app.group15.persistence.dao;

import com.app.group15.model.Course;

public class CourseDaoMock {

    public Course getCourseByCourseIdMock(String courseId) {
        Course courseEntity = new Course();

        if(courseId.equals("6")) {
            courseEntity.setName("CSCI5409");
            return courseEntity;
        }else {
            return courseEntity;
        }
    }
}
