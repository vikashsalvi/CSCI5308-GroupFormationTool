package com.app.group15.controller;

import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.services.AssignTAService;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.SessionService;
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
                UserEntity userEntity = SessionService.getSessionUser(request);
                ArrayList<CourseEntity> courseEntities= CourseService.getCoursesList();
                modelAndView = new ModelAndView();
                modelAndView.setViewName("instructor/home");
                modelAndView.addObject("userEntity", userEntity);
                modelAndView.addObject("courseEntities", courseEntities);
                return modelAndView;
            } else {
                System.out.println("----------------Unauthorized access for /instructor/home !!!----------------");
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            System.out.println("----------------User not logged in !!!----------------");
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/instructor/assign-ta", method = RequestMethod.GET)
    public ModelAndView assignTAGET(HttpServletRequest request, @RequestParam  String courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (SessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                UserEntity userEntity = SessionService.getSessionUser(request);
                ArrayList<CourseEntity> courseEntities= CourseService.getCoursesList();
                modelAndView = new ModelAndView();
                modelAndView.setViewName("instructor/ta-assignment");
                modelAndView.addObject("courseId", courseId);
                modelAndView.addObject("userEntity", userEntity);
                modelAndView.addObject("courseEntities", courseEntities);
                return modelAndView;
            } else {
                System.out.println("----------------Unauthorized access for instructor/assign-ta !!!----------------");
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            System.out.println("----------------User not logged in !!!----------------");
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/instructor/assign-ta", method = RequestMethod.POST)
    public ModelAndView assignTAPOST(HttpServletRequest request, @RequestParam(required = false, value = "bannerId") String bannerId, @RequestParam  String courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        AssignTAService assignTAService = new AssignTAService();
        if (SessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                UserEntity userEntity = SessionService.getSessionUser(request);

                modelAndView = new ModelAndView();
                modelAndView.setViewName("instructor/ta-assignment");

                if (assignTAService.validateBannerID(bannerId))
                {
                    modelAndView.addObject("error_invalid_banner",false);

                }else {
                    modelAndView.addObject("error_invalid_banner",true);
                }

                if (assignTAService.performTAUpdate(bannerId,courseId))
                {
                    modelAndView.addObject("success_ta_changed",true);
                }else {
                    modelAndView.addObject("success_ta_changed",false);
                }

                modelAndView.addObject("userEntity", userEntity);

                return modelAndView;
            } else {
                System.out.println("----------------Unauthorized access for instructor/assign-ta !!!----------------");
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            System.out.println("----------------User not logged in !!!----------------");
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }





}
