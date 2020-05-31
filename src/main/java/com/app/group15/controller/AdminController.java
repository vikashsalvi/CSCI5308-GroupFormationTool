package com.app.group15.controller;

import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class AdminController {
	private AuthorizationService authorizationService = new AuthorizationService();

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User userEntity = SessionService.getSessionUser(request);
				ArrayList<Course> courseEntities = CourseService.getCoursesList();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/home");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntities", courseEntities);
				return modelAndView;
			} else {
				System.out.println("----------------Unauthorized access for /admin/home !!!----------------");
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			System.out.println("----------------User not logged in !!!----------------");
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
}
