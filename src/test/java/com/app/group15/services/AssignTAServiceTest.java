package com.app.group15.services;

import com.app.group15.persistence.dao.CourseDao;
import com.app.group15.persistence.dao.CourseDaoMock;
import com.app.group15.persistence.dao.UserDaoMock;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignTAServiceTest {

    private UserDaoMock userDaoMock = new UserDaoMock();
    private CourseDaoMock courseDaoMock = new CourseDaoMock();

    @Test
    void performTAUpdate() {

    }

    @Test
    void validateBannerID() {
        // checks that user is registered with system
        UserEntity userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
        assertNotNull(userEntity.getId());
    }

    @Test
    void validateCourseID() {
        CourseEntity courseEntity = courseDaoMock.getCourseByCourseIdMock("6");
        assertEquals(courseEntity.getName(),"CSCI5");
    }
}