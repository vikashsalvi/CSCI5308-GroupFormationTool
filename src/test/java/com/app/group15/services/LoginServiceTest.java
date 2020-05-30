package com.app.group15.services;

import com.app.group15.persistence.dao.UserDaoMock;
import com.app.group15.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private UserDaoMock userDaoMock = new UserDaoMock();

    @Test
    public void validateLoginTest() {
        UserEntity userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
        assertEquals(userEntity.getFirstName(), "Daksh");

        userEntity = userDaoMock.getUserByBannerIdMock("B00843468");
        assertNull(userEntity.getPassword());
    }
}