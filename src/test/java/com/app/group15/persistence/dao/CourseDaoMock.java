package com.app.group15.persistence.dao;

import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;

public class CourseDaoMock {

    public CourseEntity getCourseByCourseIdMock(String courseId) {
        CourseEntity courseEntity = new CourseEntity();

        if(courseId.equals("6")) {
            courseEntity.setName("CSCI5409");
            return courseEntity;
        }else {
            return courseEntity;
        }
    }
}
