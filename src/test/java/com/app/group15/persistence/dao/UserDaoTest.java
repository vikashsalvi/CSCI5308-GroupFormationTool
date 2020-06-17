package com.app.group15.persistence.dao;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.group15.userManagement.User;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDaoTest {
    UserDaoMock userDaoMock = new UserDaoMock();
    @Test
    public void getUserByBannerIdTest() throws ParseException {
        User user = userDaoMock.getUserByBannerIdMock("B00843468");
        assertEquals("B00843468", user.getBannerId());
    }

}
