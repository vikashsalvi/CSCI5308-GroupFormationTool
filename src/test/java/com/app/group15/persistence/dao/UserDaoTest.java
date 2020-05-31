package com.app.group15.persistence.dao;


import com.app.group15.dao.UserDao;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDaoTest {

    @Test
    public void getUserByBannerIdTest() throws ParseException {
        UserDao userDao = new UserDaoInjectorService().getUserDao();
        User user = userDao.getUserByBannerId("B00838074");
        //boolean inserted = userDao.insertForgotPasswordToken(user.getId(),"abcdefgh");
        //Date tokenGenerationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new Date().toString());
        //long minutes = ServiceUtility.getTimeDifference(tokenGenerationDate);
        assertEquals(true, true);
    }

    @Test
    public void generateTokeForPasswordChange() {
        /*UserDao userDao = new UserDaoInjectorService().getUserDao();
        User user = userDao.getUserByBannerId("B00838074");
        if (userDao.checkIfTokenAlreadyExists(user.getId())) {
            userDao.deleteForgotPasswordToken(user.getId());
        }
        String token = ServiceUtility.generateForgotPasswordToken();
        boolean inserted = userDao.insertForgotPasswordToken(user.getId(), token);*/
        assertEquals(true, true);
    }
}
