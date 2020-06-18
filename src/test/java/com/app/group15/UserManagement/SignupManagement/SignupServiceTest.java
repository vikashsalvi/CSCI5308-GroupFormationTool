package com.app.group15.UserManagement.SignupManagement;

import com.app.group15.UserManagement.User;
import com.app.group15.UserManagement.UserDaoMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SignupServiceTest {

    private UserDaoMock userDaoMock = new UserDaoMock();

    @Test
    public void checkUserExistsTest() {
        User user = userDaoMock.getUserByBannerIdMock("B00843468");
        assertEquals(user.getFirstName(), "Daksh");

        user = userDaoMock.getUserByBannerIdMock("B00843469");
        assertNull(user.getFirstName());
    }

    @Test
    public void createUserTest() {
        User user = new User();
        int userId = userDaoMock.saveUserMock(user, "GUEST");
        assertEquals(userId, 0);
    }


}
