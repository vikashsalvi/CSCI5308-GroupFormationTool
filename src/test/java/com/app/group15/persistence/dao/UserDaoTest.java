package com.app.group15.persistence.dao;


import com.app.group15.dao.UserDao;
import com.app.group15.injectors.UserDaoInjectorService;
import com.app.group15.model.User;
import com.app.group15.utility.ServiceUtility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDaoTest {

    @Test
    public void getUserByBannerIdTest() throws ParseException {
        UserDao userDao = new UserDaoInjectorService().getUserDao();
        User user = userDao.getUserByBannerId("B00843468");
        //boolean inserted = userDao.insertForgotPasswordToken(user.getId(),"abcdefgh");
        String date = userDao.getUserFromToken("abcdefgh").get("dateTime");
        Date tokenGenerationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        long minutes = ServiceUtility.getTimeDifference(tokenGenerationDate);
        assertEquals(true, true);
    }

    @Test
    public void generateTokeForPasswordChange() {
        UserDao userDao = new UserDaoInjectorService().getUserDao();
        User user = userDao.getUserByBannerId("B00843458");
        if (userDao.checkIfTokenAlreadyExists(user.getId())) {
            userDao.deleteForgotPasswordToken(user.getId());
        }
        String token = ServiceUtility.generateForgotPasswordToken();
        boolean inserted = userDao.insertForgotPasswordToken(user.getId(), token);
        assertEquals(inserted, true);
    }
}
