package com.app.group15.SurveyManagement;


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
import java.util.logging.Level;

@Controller
public class SurveyController {

    private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
    private CourseAbstractDao courseDao = AppConfig.getInstance().getCourseDao();
    private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();

    @RequestMapping(value = "/instructor/survey", method = RequestMethod.GET)
    public ModelAndView manageSurveyGET(HttpServletRequest request, @RequestParam String courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {

                    User userEntity = sessionService.getSessionUser(request);
                    Course courseEntity = (Course) courseDao.get(Integer.parseInt(courseId));

                    modelAndView = new ModelAndView();
                    modelAndView.setViewName("instructor/survey");
                    modelAndView.addObject("courseId", courseId);
                    modelAndView.addObject("userEntity", userEntity);
                    modelAndView.addObject("courseEntity", courseEntity);
                    

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
