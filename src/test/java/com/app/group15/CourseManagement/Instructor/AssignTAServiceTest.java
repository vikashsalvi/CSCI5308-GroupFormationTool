package com.app.group15.CourseManagement.Instructor;

import com.app.group15.CourseManagement.Course;
import com.app.group15.CourseManagement.CourseDaoMock;
import com.app.group15.UserManagement.User;
import com.app.group15.UserManagement.UserDaoMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        userEntity = userDaoMock.getUserByBannerIdMock("B003468");
        assertEquals(userEntity.getId(),0);
    }

    @Test
    void validateCourseIDTest() {
        Course courseEntity = courseDaoMock.getCourseByCourseIdMock("6");
        assertEquals(courseEntity.getName(),"CSCI5409");

        courseEntity = courseDaoMock.getCourseByCourseIdMock("7");
        assertNull(courseEntity.getName(), (String) null);

    }


    @Test
    void checkIntructorPermissionTest() {
       CourseInstructorMapper courseInstructorMapperEntity = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
       assertEquals(courseInstructorMapperEntity.getInstructorId(), 17);

        courseInstructorMapperEntity = courseInstructorMapperDaoMock.getCourseInstructorMapperEntity(6);
        assertNotEquals(courseInstructorMapperEntity.getInstructorId(), 16);
    }
}