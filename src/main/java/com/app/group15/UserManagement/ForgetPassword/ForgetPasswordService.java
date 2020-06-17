package com.app.group15.UserManagement.ForgetPassword;

import com.app.group15.UserManagement.User;
import com.app.group15.UserManagement.UserDao;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;
import com.app.group15.config.AppConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

public class ForgetPasswordService implements IForgetPasswordService, IForgetPasswordServiceInjector {

    private ForgetPasswordAbstractDao forgetPasswordDao;
    private UserDao userDao;


    @Override
    public void generateToken(String bannerId, HttpServletRequest request) {

        try {
            User user = userDao.getUserByBannerId(bannerId);

            String token = ForgetPasswordUtility.generateForgotPasswordToken();
            if (forgetPasswordDao.checkIfTokenAlreadyExists(user.getId())) {
                forgetPasswordDao.deleteForgotPasswordToken(user.getId());
            }

            forgetPasswordDao.insertForgotPasswordToken(user.getId(), token);

            String urlGenerated = request.getRequestURL().toString();
            urlGenerated = urlGenerated.substring(0, urlGenerated.lastIndexOf('/'));
            token = URLEncoder.encode(token, StandardCharsets.UTF_8.toString());
            urlGenerated += "/auth/validateToken/?to=" + token;

            String subject = "CATME Password Change request";
            String body = "Click this link to change password: " + urlGenerated;
            AppConfig.getInstance().getEmailNotifier().sendMessage(user.getEmail(), subject, body);

        } catch (UnsupportedEncodingException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public boolean verifyToken(String token) {
        if (ServiceUtility.isNotNull(token)) {

            boolean validated = false;
            Map<String, String> user = forgetPasswordDao.getUserFromToken(token);
            String tokenGenerationDateTime = user.get("dateTime");
            Date tokenGenerationDate = null;
            try {
                tokenGenerationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokenGenerationDateTime);
                long minutes = ForgetPasswordUtility.getTimeDifference(tokenGenerationDate);
                if (minutes > 60) {
                    forgetPasswordDao.deleteForgotPasswordToken(Integer.parseInt(user.get("id")));
                    validated = false;
                }
                validated = true;
            } catch (ParseException e) {
                GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            }

            return validated;

        } else {
            GroupFormationToolLogger.log(Level.SEVERE, "Invalid input");
            return false;
        }
    }

    @Override
    public boolean deleteForgotPasswordToken(int id) {
        boolean deleted = forgetPasswordDao.deleteForgotPasswordToken(id);
        return deleted;
    }

    @Override
    public Map<String, String> getUserFromToken(String token) {

        Map<String, String> user = forgetPasswordDao.getUserFromToken(token);
        return user;
    }

    @Override
    public boolean updateUserPassword(int id, String newPassword) {
        boolean updated = forgetPasswordDao.updateUserPassword(id, newPassword);
        return updated;
    }

    @Override
    public void injectUserDao(UserDao userDao) {
        if (ServiceUtility.isNotNull(userDao)) {
            this.userDao = userDao;
        } else {
            GroupFormationToolLogger.log(Level.SEVERE, "userDao injection problems in ForgetPasswordService");
        }
    }

    @Override
    public void injectForgetPasswordDao(ForgetPasswordAbstractDao forgetPasswordDao) {
        if (ServiceUtility.isNotNull(forgetPasswordDao)) {
            this.forgetPasswordDao = forgetPasswordDao;
        } else {
            GroupFormationToolLogger.log(Level.SEVERE, "forgetPasswordDao injection problems in ForgetPasswordService");
        }
    }
}
