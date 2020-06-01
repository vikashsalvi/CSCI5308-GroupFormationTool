package com.app.group15.services;

import com.app.group15.persistence.dao.*;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.CourseInstructorMapperEntity;
import com.app.group15.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignTAServiceTest {

    private UserDaoMock userDaoMock = new UserDaoMock();
    private CourseDaoMock courseDaoMock = new CourseDaoMock();
    private CourseInstructorMapperDaoMock courseInstructorMapperDaoMock = new CourseInstructorMapperDaoMock();

    @Test
    void performTAUpdateTest() {
        CourseInstructorMapperEntity courseInstructorMapperEntity = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
        // 0 means TA not assigned
        assertEquals(courseInstructorMapperEntity.getTaId(), 0);
        courseInstructorMapperDaoMock.setTA(6);
        assertNotNull(courseInstructorMapperEntity.getTaId());
    }

    @Test
    void validateBannerIDTest() {
        // checks that user is registered with system
        UserEntity userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
        assertNotNull(userEntity.getId());
    }

    @Test
    void validateCourseIDTest() {
        CourseEntity courseEntity = courseDaoMock.getCourseByCourseIdMock("6");
        assertEquals(courseEntity.getName(),"CSCI5409");
    }


    @Test
    void checkIntructorPermissionTest() {

       CourseInstructorMapperEntity courseInstructorMapperEntity = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
       assertEquals(courseInstructorMapperEntity.getInstructorId(), 17);
    }
}