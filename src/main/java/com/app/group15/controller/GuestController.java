package com.app.group15.controller;

import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;
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
public class GuestController {
	private AuthorizationService authorizationService = new AuthorizationService();

	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public ModelAndView guestHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"GUEST"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				UserEntity userEntity = SessionService.getSessionUser(request);
				ArrayList<CourseEntity> courseEntities= CourseService.getCoursesList();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("home");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntities", courseEntities);
				return modelAndView;
			} else {
//				System.out.println("----------------Unauthorized access for /user/home !!!----------------");
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
//			System.out.println("----------------User not logged in !!!----------------");
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
}
