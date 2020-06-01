package com.app.group15.controller;

import com.app.group15.persistence.dao.CourseDao;
import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.CourseInstructorMapperEntity;
import com.app.group15.persistence.entity.CourseStudentMapperEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.CourseDaoInjectorService;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import com.app.group15.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class InstructorController {
    private AuthorizationService authorizationService = new AuthorizationService();

    @RequestMapping(value = "/instructor/home", method = RequestMethod.GET)
    public ModelAndView adminHome(HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (SessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {

                InstructorService instructorService = new InstructorService();

                UserEntity userEntity = SessionService.getSessionUser(request);
                ArrayList<CourseEntity> courseEntities = instructorService.getCourseOfInstructor((userEntity.getId()));
                ArrayList<UserEntity> userEntitiesTA=InstructorService.getAllCourseTA(courseEntities);

                modelAndView = new ModelAndView();
                modelAndView.setViewName("instructor/home");
                modelAndView.addObject("userEntity", userEntity);
                modelAndView.addObject("courseEntities", courseEntities);
                modelAndView.addObject("userEntitiesTA", userEntitiesTA);

                return modelAndView;
            } else {
                //Unauthorized access for /instructor/home
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in !!!
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/instructor/assign-ta", method = RequestMethod.GET)
    public ModelAndView assignTAGET(HttpServletRequest request, @RequestParam String courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (SessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {

                UserEntity userEntity = SessionService.getSessionUser(request);
                CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
                CourseEntity courseEntity =  courseDao.get(Integer.parseInt(courseId));

                modelAndView = new ModelAndView();
                modelAndView.setViewName("instructor/ta-assignment");
                modelAndView.addObject("courseId", courseId);
                modelAndView.addObject("userEntity", userEntity);
                modelAndView.addObject("courseEntity", courseEntity);

                return modelAndView;
            } else {
                //Unauthorized access for instructor/assign-ta
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/instructor/assign-ta", method = RequestMethod.POST)
    public ModelAndView assignTAPOST(HttpServletRequest request, @RequestParam(required = true, value = "bannerId") String bannerId, @RequestParam(required = true, value = "courseId") int courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        AssignTAService assignTAService = new AssignTAService();
        if (SessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {

                UserEntity userEntity = SessionService.getSessionUser(request);
                InstructorService instructorService = new InstructorService();
                ArrayList<CourseEntity> courseEntities = instructorService.getCourseOfInstructor((userEntity.getId()));
                ArrayList<UserEntity> userEntitiesTA=InstructorService.getAllCourseTA(courseEntities);
                CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
                CourseEntity courseEntity =  courseDao.get(courseId);
                modelAndView = new ModelAndView();

                if (assignTAService.validateBannerID(bannerId)) {
                    modelAndView.addObject("error", false);
                    if (assignTAService.performTAUpdate(bannerId,courseId))
                    {
                        modelAndView.setViewName("redirect:/instructor/home");
                        return modelAndView;
                    }
                }
                else {
                    modelAndView.addObject("error", true);
                }

                modelAndView.setViewName("instructor/ta-assignment");
                modelAndView.addObject("courseId", courseId);
                modelAndView.addObject("userEntity", userEntity);
                modelAndView.addObject("courseEntity", courseEntity);
                modelAndView.addObject("userEntitiesTA", userEntitiesTA);
                return modelAndView;

            } else {
                //Unauthorized access for instructor/assign-ta
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in !
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }


}
