package com.app.group15.services;

import com.app.group15.model.Course;
import com.app.group15.model.CourseInstructorMapper;
import com.app.group15.model.User;
import com.app.group15.persistence.dao.CourseDaoMock;
import com.app.group15.persistence.dao.CourseInstructorMapperDaoMock;
import com.app.group15.persistence.dao.UserDaoMock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AssignTAServiceTest {

    private UserDaoMock userDaoMock = new UserDaoMock();
    private CourseDaoMock courseDaoMock = new CourseDaoMock();
    private CourseInstructorMapperDaoMock courseInstructorMapperDaoMock = new CourseInstructorMapperDaoMock();

    @Test
    void performTAUpdateTest() {
        CourseInstructorMapper courseInstructorMapperEntity = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
        // 0 means TA not assigned
        assertEquals(courseInstructorMapperEntity.getTaId(), 0);
        courseInstructorMapperDaoMock.setTA(6);
        assertNotNull(courseInstructorMapperEntity.getTaId());
    }

    @Test
    void validateBannerIDTest() {
        // checks that user is registered with system
        User userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
        assertNotNull(userEntity.getId());
    }

    @Test
    void validateCourseIDTest() {
        Course courseEntity = courseDaoMock.getCourseByCourseIdMock("6");
        assertEquals(courseEntity.getName(),"CSCI5409");
    }


    @Test
    void checkIntructorPermissionTest() {

       CourseInstructorMapper courseInstructorMapperEntity = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
       assertEquals(courseInstructorMapperEntity.getInstructorId(), 17);
    }
}