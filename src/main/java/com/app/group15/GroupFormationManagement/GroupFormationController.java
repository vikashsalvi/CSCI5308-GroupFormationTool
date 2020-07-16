package com.app.group15.GroupFormationManagement;

import com.app.group15.Config.AppConfig;
import com.app.group15.Config.ServiceConfig;
import com.app.group15.CourseManagement.Course;
import com.app.group15.CourseManagement.CourseAbstractDao;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.UserManagement.SessionManagement.IAuthorizationService;
import com.app.group15.UserManagement.SessionManagement.ISessionService;
import com.app.group15.UserManagement.User;
import com.app.group15.Utility.GroupFormationToolLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

@Controller
public class GroupFormationController {

    private IGroupFormationService groupFormationService = ServiceConfig.getInstance().getGroupFormationService();
    private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
    private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
    private CourseAbstractDao courseDao = AppConfig.getInstance().getCourseDao();


    @RequestMapping(value = "/instructor/generate-groups", method = RequestMethod.POST)
    public ModelAndView getSurveyPage(HttpServletRequest request,
                                      @RequestParam(value = "courseId", required = true) int courseId, @RequestParam(value = "size", required = true) int size) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {

                    User user = sessionService.getSessionUser(request);
                    Course courseEntity = (Course) courseDao.get(Integer.parseInt(String.valueOf(courseId)));
                    ArrayList<ArrayList<User>> listOfTeams = groupFormationService.formGroups(courseId, size);

                    modelAndView = new ModelAndView();
                    modelAndView.addObject("user", user);
                    modelAndView.addObject("courseEntity", courseEntity);
                    modelAndView.addObject("listOfTeams", listOfTeams);
                    modelAndView.setViewName("instructor/groups");
                    return modelAndView;
                } else {
                    modelAndView = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }
            return modelAndView;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /dbError endpoint ");
            modelAndView = new ModelAndView("dbError");
            return modelAndView;
        } catch (AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /awsError endpoint ");
            modelAndView = new ModelAndView("awsError");
            return modelAndView;
        }
    }

}
