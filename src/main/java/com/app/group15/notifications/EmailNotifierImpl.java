package com.app.group15.notifications;

import com.app.group15.config.AppConfig;
import com.app.group15.utility.AwsSecretsManagerUtility;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
import java.util.logging.Level;

public class EmailNotifierImpl implements INotifier {

    static Properties springProperties;

    static {
        springProperties = AppConfig.getInstance().getProperties();
    }

    private JavaMailSenderImpl javaMailSender;

    @Override
    public boolean setCredentials() {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(springProperties.getProperty("mail.host"));
        javaMailSender.setPort(Integer.parseInt(springProperties.getProperty("mail.port")));
        javaMailSender.setUsername(AwsSecretsManagerUtility.getSmtpEmail());
        javaMailSender.setPassword(AwsSecretsManagerUtility.getSmtpPassword());

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", springProperties.getProperty("mail.transport.protocol"));
        props.put("mail.smtp.auth", springProperties.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", springProperties.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.debug", springProperties.getProperty("mail.debug"));
        return true;
    }

    @Override
    public boolean sendMessage(String receiptEmail, String subject, String message) {
        setCredentials();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(receiptEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException ex) {
            GroupFormationToolLogger.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }

        return true;
    }
}
