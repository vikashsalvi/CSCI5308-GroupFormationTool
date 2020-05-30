package com.app.group15.notifications;

import com.app.group15.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class EmailNotifierImplTest {

    Notifier emailNotifier = AppConfig.getInstance().getEmailNotifier();

    @Test
    void sendMessageTest(){

        String receiptEmail = "thanksfordemo@gmail.com";
        String subject = "Hi test message";
        String message = "Test";
        assertEquals(emailNotifier.sendMessage(receiptEmail,subject,message),true);
    }

    @Test
    void setCredentialsTest(){
        assertEquals(emailNotifier.setCredentials(),true);
    }
}
