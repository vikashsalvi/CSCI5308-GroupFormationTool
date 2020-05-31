package com.app.group15.notifications;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class EmailNotifierImplTest {

    EmailNotifierImpl emailNotifier = new EmailNotifierImpl();

    @Test
    void sendMessageTest() {

        String receiptEmail = "thanksfordemo@gmail.com";
        String subject = "Hi test message";
        String message = "Test";
        assertEquals(emailNotifier.sendMessage(receiptEmail, subject, message), true);
    }

    @Test
    void setCredentialsTest() {
        assertEquals(emailNotifier.setCredentials(), true);
    }
}
