package com.app.group15.controller;

import com.app.group15.config.AppConfig;
import com.app.group15.dao.UserAbstractDao;

import com.app.group15.model.User;
import com.app.group15.utility.ForgetPasswordUtility;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

@Controller
@RequestMapping("/")
public class ForgetPasswordController {

	private UserAbstractDao userDao=AppConfig.getInstance().getUserDao();

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public ModelAndView returnModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sent", false);
        modelAndView.setViewName("forgetPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ModelAndView getUserAndGenerateToken(@RequestParam(required = true, value = "bannerId") String bannerId, HttpServletRequest request) throws UnsupportedEncodingException {

        User user = userDao.getUserByBannerId(bannerId);
        String token = ForgetPasswordUtility.generateForgotPasswordToken();
        if (userDao.checkIfTokenAlreadyExists(user.getId())) {
            userDao.deleteForgotPasswordToken(user.getId());
        }
        boolean inserted = userDao.insertForgotPasswordToken(user.getId(), token);

        String urlGenerated = request.getRequestURL().toString();
        urlGenerated = urlGenerated.substring(0, urlGenerated.lastIndexOf('/'));
        token = URLEncoder.encode(token, StandardCharsets.UTF_8.toString());
        urlGenerated += "/auth/validateToken/?to=" + token;

        String subject = "CATME Password Change request";
        String body = "Click this link to change password: " + urlGenerated;
        AppConfig.getInstance().getEmailNotifier().sendMessage(user.getEmail(), subject, body);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgetPassword");
        modelAndView.addObject("sent", true);
        modelAndView.addObject("completed", false);
        modelAndView.addObject("bannerId_error", "Please enter BannerId");
        return modelAndView;
    }


    @RequestMapping("/auth/validateToken")
    public ModelAndView verifyToken(@RequestParam("to") String token) {
        boolean validated = false;
       
        Map<String, String> user = userDao.getUserFromToken(token);
        String tokenGenerationDateTime = user.get("dateTime");
        Date tokenGenerationDate = null;
        try {
            tokenGenerationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokenGenerationDateTime);
            long minutes = ForgetPasswordUtility.getTimeDifference(tokenGenerationDate);
            if (minutes > 60) {
                userDao.deleteForgotPasswordToken(Integer.parseInt(user.get("id")));
                validated = false;
            }
            validated = true;
        } catch (ParseException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        if (validated) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", false);
            modelAndView.addObject("completed", false);
            //modelAndView.addObject("password_error", false);
            modelAndView.setViewName("resetPassword");
            return modelAndView;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam(required = false, value = "token") String token,
                                       @RequestParam(required = true, value = "password") String password,
                                       @RequestParam(required = true, value = "cPassword") String newPassword) {

        if (!newPassword.equals(password)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("resetPassword");
            modelAndView.addObject("error", true);
            modelAndView.addObject("completed", false);
            modelAndView.addObject("password_error", "Password did not match!");
            return modelAndView;
        }
        Map<String, String> user = userDao.getUserFromToken(token);
        boolean passed = false;
        if (userDao.updateUserPassword(Integer.parseInt(user.get("id")), newPassword)) {
            passed = userDao.deleteForgotPasswordToken(Integer.parseInt(user.get("id")));
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("resetPassword");
            modelAndView.addObject("error", false);
            modelAndView.addObject("completed", true);
            return modelAndView;
        }

        return null;
    }
}
