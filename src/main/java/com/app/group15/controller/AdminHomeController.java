package com.app.group15.controller;

import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.SessionServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminHomeController {
	private AuthorizationService authorizationService = new AuthorizationService();

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionServices.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				UserEntity userEntity = SessionServices.getSessionUser(request);
				modelAndView = new ModelAndView();
				modelAndView.setViewName("home");
				modelAndView.addObject("userEntity", userEntity);
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
